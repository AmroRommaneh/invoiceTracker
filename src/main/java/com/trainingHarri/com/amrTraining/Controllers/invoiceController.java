package com.trainingHarri.com.amrTraining.Controllers;

import com.trainingHarri.com.amrTraining.DTOs.invoiceDto;
import com.trainingHarri.com.amrTraining.Model.invoice;
import com.trainingHarri.com.amrTraining.Repositries.invoiceItemRepo;
import com.trainingHarri.com.amrTraining.Repositries.invoiceRepo;
import com.trainingHarri.com.amrTraining.Repositries.invoiceRepoPa;
import com.trainingHarri.com.amrTraining.Services.invoiceItemService;
import com.trainingHarri.com.amrTraining.Services.invoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



    @PostMapping(path = "/addInvoice")
    public ResponseEntity<String> addInvoice(@RequestBody invoiceDto invoiceDto,@RequestHeader String Authorization) {

        invoice invoice = new invoice();
        System.out.println("reeeched1");
        invoice.setAmount(invoiceDto.getAmount());
        invoice.setExternalInvoiceId(invoiceDto.getExternalInvoiceId());
        invoice.setDateOfCreation(invoiceDto.getDateOfCreation());
        invoiceService.addInvoice(invoice,Authorization);
saveItems(invoiceDto.getItems(),invoice.getInvoiceId());
        return new ResponseEntity<>("the invoice has been added", HttpStatus.OK);
    }

    @PreAuthorize("!hasRole('ROLE_AUDITOR')")
    @DeleteMapping(path = "/deleteInvoice")
    public ResponseEntity<String> deleteInvoice(@RequestBody invoiceDto invoiceDto,@RequestHeader String Authorization) {

       invoiceService.deleteInvoice(invoiceDto,Authorization);
        return new ResponseEntity<>("the invoice has been deleted", HttpStatus.OK);

    }


  @PreAuthorize("!hasRole('ROLE_AUDITOR')")
    @PutMapping(path = "/updateInvoice")
    public ResponseEntity<String> updateInvoice(@RequestBody invoiceDto invoiceDto,@RequestHeader String Authorization) {

    invoiceService.update(invoiceDto,Authorization);
        return new ResponseEntity<>("the invoice has been updated", HttpStatus.OK);

    }

    @GetMapping(path = "/getAllInvoices")
    public ResponseEntity<Page<invoice>> getInvoices(Pageable page) {
     //   Pageable firstPageWithTenElements = PageRequest.of(0, 10, Sort.by("invoiceId").descending());

System.out.println("weewn ward"+invoiceRepoPa.findAll(page));
        return new ResponseEntity<>(invoiceService.findAll(page), HttpStatus.OK);
    }


    @GetMapping(path = "/getItemsByInvoice/{invoiceId}")
    public ResponseEntity<List<Long>> getItemByinvoice(@PathVariable Long invoiceId) {
        System.out.println("iddddddddddd is "+invoiceId);
        System.out.println("items id "+ invoiceItemRepo.findd(invoiceId));
        return new ResponseEntity<>(invoiceItemRepo.findd(invoiceId), HttpStatus.OK);
    }


    public void saveItems(List<Long> items,long invoiceId){

        for (int i =0;i<items.size();i++){
            invoiceItemService.saveInvoiceItem(invoiceId,items.get(i));
        }

    }
}