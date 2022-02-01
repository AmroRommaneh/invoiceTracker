package com.trainingHarri.com.amrTraining.Controllers;

import com.trainingHarri.com.amrTraining.DTOs.invoiceDto;
import com.trainingHarri.com.amrTraining.Model.invoice;
import com.trainingHarri.com.amrTraining.Repositries.invoiceRepo;
import com.trainingHarri.com.amrTraining.Services.invoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class invoiceController {

    @Autowired
    invoiceService invoiceService;
    @Autowired
    invoiceRepo invoiceRepo;

    @PostMapping(path = "/addInvoice")
    public ResponseEntity<String> addInvoice(@RequestBody invoiceDto invoiceDto) {
        invoice invoice = new invoice();
        System.out.println("reeeched1");
        invoice.setAmount(invoiceDto.getAmount());
        invoice.setExternalInvoiceId(invoiceDto.getExternalInvoiceId());
        invoice.setDateOfCreation(invoiceDto.getDateOfCreation());
        invoiceService.addInvoice(invoice);

        return new ResponseEntity<>("the item has been added", HttpStatus.OK);
    }


    @DeleteMapping(path = "/deleteInvoice")
    public ResponseEntity<String> deleteInvoice(@RequestBody invoiceDto invoiceDto) {

        invoiceService.deleteInvoice(invoiceDto);
        return new ResponseEntity<>("the item has been deleted", HttpStatus.OK);

    }

    @PutMapping(path = "/updateInvoice")
    public ResponseEntity<String> updateInvoice(@RequestBody invoiceDto invoiceDto) {

        invoiceService.update(invoiceDto);
        return new ResponseEntity<>("the item has been updated", HttpStatus.OK);

    }

    @GetMapping(path = "/getAllInvoices")
    public ResponseEntity<List<invoice>> getInvoices() {

        return new ResponseEntity<>(invoiceRepo.findAll(), HttpStatus.OK);
    }
}