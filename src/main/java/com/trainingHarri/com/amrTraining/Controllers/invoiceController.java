package com.trainingHarri.com.amrTraining.Controllers;

import com.trainingHarri.com.amrTraining.DTOs.invoiceDto;
import com.trainingHarri.com.amrTraining.Model.invoice;
import com.trainingHarri.com.amrTraining.Model.invoiceItem;
import com.trainingHarri.com.amrTraining.Repositries.invoiceItemRepo;
import com.trainingHarri.com.amrTraining.Repositries.invoiceRepo;
import com.trainingHarri.com.amrTraining.Repositries.invoiceRepoPa;
import com.trainingHarri.com.amrTraining.Services.invoiceItemService;
import com.trainingHarri.com.amrTraining.Services.invoiceService;
import com.trainingHarri.com.amrTraining.exceptions.customExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/Invoice")


public class invoiceController {

    @Autowired
    invoiceService invoiceService;
    @Autowired
    invoiceRepo invoiceRepo;
    @Autowired
    invoiceItemRepo invoiceItemRepo;
    @Autowired
    invoiceRepoPa invoiceRepoPa;
    @Autowired
    invoiceItemService invoiceItemService;


    @PreAuthorize("!hasRole('ROLE_AUDITOR')")
    @PostMapping(path = "/addInvoice")
    public ResponseEntity<String> addInvoice(@RequestBody invoiceDto invoiceDto,@RequestHeader String Authorization) {
        System.out.println(invoiceDto.toString());
List<Long> items= invoiceDto.getItems();
       try {

            invoiceService.checkItems(items);
        }catch (customExeption e){

            System.out.println(e.getErrorMessage());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error",e.getErrorMessage());

            return ResponseEntity.badRequest().headers(responseHeaders).body(e.getErrorMessage());

        }
        invoice invoice = new invoice();
        System.out.println("reeeched1");
        invoice.setAmount(invoiceDto.getAmount());
        invoice.setExternalInvoiceId(invoiceDto.getExternalInvoiceId());
        invoice.setDateOfCreation(invoiceDto.getDateOfCreation());

        try {
            invoiceService.addInvoice(invoice,Authorization);

        }catch (customExeption x){
System.out.println("message "+x.getErrorMessage());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error",x.getErrorMessage());

            return ResponseEntity.badRequest().headers(responseHeaders).body(x.getErrorMessage());
        }


        saveItems(invoiceDto.getItems(), invoiceDto.getQuantity(),invoice.getInvoiceId());



       return new ResponseEntity<>("the invoice has been added", HttpStatus.OK);
    }

    @PreAuthorize("!hasRole('ROLE_AUDITOR')")
    @DeleteMapping(path = "/deleteInvoice/{externalInvoiceId}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long externalInvoiceId,@RequestHeader String Authorization) {
      invoice x =invoiceRepo.findByexid(externalInvoiceId);
      String y= invoiceService.deleteInvoice(x,Authorization);

        if(y.equals("NO INVOICE EXIST WITH THIS EXTERNAL INVOICE ID")){

          return new ResponseEntity<>("NO INVOICE EXIST WITH THIS EXTERNAL INVOICE ID", HttpStatus.OK);

      }else if(y.equals("INVOICE HAS BEEN DELETED")) {
              return new ResponseEntity<>(y, HttpStatus.OK);
          }else if(y.equals("YOU ARE NOT ALLOWED TO MAKE UPDATE"))
              return new ResponseEntity<>("YOU ARE NOT ALLOWED TO MAKE DELETE", HttpStatus.OK);

        return new ResponseEntity<>("YOU ARE NOT ALLOWED TO MAKE DELETE", HttpStatus.OK);
    }


  @PreAuthorize("!hasRole('ROLE_AUDITOR')")
    @PutMapping(path = "/updateInvoice")
    public ResponseEntity<String> updateInvoice(@RequestBody invoiceDto invoiceDto,@RequestHeader String Authorization) {
      List<Long> items= invoiceDto.getItems();
      try {

          invoiceService.checkItems(items);
      }catch (customExeption e){

          System.out.println(e.getErrorMessage());
          HttpHeaders responseHeaders = new HttpHeaders();
          responseHeaders.set("error",e.getErrorMessage());

          return ResponseEntity.badRequest().headers(responseHeaders).body(e.getErrorMessage());

      }

      System.out.println(invoiceDto.toString());
   String x= invoiceService.update(invoiceDto,Authorization);
   System.out.println("XXXXXXXXXX" +x);
   if(x.equals("NO INVOICE FOUND WITH THIS EXTERNAL INVOICE ID")) {
       return new ResponseEntity<>(x, HttpStatus.OK);
   }else  if(x.equals("INVOICE HAS BEEN UPDATED")) {
       return new ResponseEntity<>(x, HttpStatus.OK);
   }else
   if(x.equals("YOU ARE NOT ALLOWED TO MAKE UPDATE"))
      return new ResponseEntity<>("YOU ARE NOT ALLOWED TO MAKE UPDATE", HttpStatus.OK);


   return null;
    }

    @GetMapping(path = "/getAllInvoices")
    public ResponseEntity<Page<invoice>> getInvoices(Pageable page,@RequestHeader String Authorization) {
     //   Pageable firstPageWithTenElements = PageRequest.of(0, 10, Sort.by("invoiceId").descending());
System.out.println(page.getPageNumber() +"     ffffff     "+page.getPageSize() + page.getSort());

//System.out.println("weewn ward"+invoiceRepoPa.findAll(page));
        return new ResponseEntity<>(invoiceService.findAll(page,Authorization), HttpStatus.OK);
    }

    @GetMapping(path = "/getItemsByInvoice/{invoiceId}")
    public ResponseEntity<List<invoiceItem>> getItemByinvoice(@PathVariable Long invoiceId) {
        System.out.println("iddddddddddd is "+invoiceId);
        System.out.println("items id "+ invoiceItemRepo.findd(invoiceId));


        return new ResponseEntity<>(invoiceService.findd(invoiceId), HttpStatus.OK);
    }

    @GetMapping(path = "/getInvoiceByEx/{externalInvoiceId}")
    public ResponseEntity<Map<String,Object>> getInvoice(@PathVariable Long externalInvoiceId) {
      //  invoice x =invoiceService.getInvoice(externalInvoiceId);
       // System.out.println(x.getExternalInvoiceId());
        return new ResponseEntity<>(invoiceService.getInvoice(externalInvoiceId), HttpStatus.OK);
    }

    public void saveItems(List<Long>items,List<Long> quantity ,long invoiceId){

        for (int i =0;i<items.size();i++){
            invoiceItemService.saveInvoiceItem(invoiceId,items.get(i), quantity.get(i));
        }

    }
}