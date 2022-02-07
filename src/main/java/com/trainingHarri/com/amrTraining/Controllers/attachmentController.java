package com.trainingHarri.com.amrTraining.Controllers;

import com.trainingHarri.com.amrTraining.AmrTrainingApplication;
import com.trainingHarri.com.amrTraining.Model.invoice;
import com.trainingHarri.com.amrTraining.Repositries.attachmentRepo;
import com.trainingHarri.com.amrTraining.Services.FileLocationService;
import com.trainingHarri.com.amrTraining.Services.attachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/attachment")
@Slf4j
public class attachmentController {

    @Autowired
    attachmentService attachmentService;
    @Autowired
    attachmentRepo attachmentRepo;
    @Autowired
    com.trainingHarri.com.amrTraining.Repositries.invoiceRepo invoiceRepo;

//    @PostMapping(path = "/addAttachment")
//    public ResponseEntity<String> addAttachment(@RequestBody attachmentDto attachmentdto) {
//        invoice invoice = invoiceRepo.findByid(attachmentdto.getInvoice());
//
//        attach.setAttachmentType(attachmentdto.getAttachmentType());
//        attach.setInvoice(invoice);
//
//        return new ResponseEntity<>("the attachment has been added", HttpStatus.OK);
//    }




    @Autowired
    FileLocationService fileLocationService;

    @PostMapping("/image/{externalInvoiceId}")
    ResponseEntity<Map<String, Object>> uploadImage(@RequestParam MultipartFile image, @PathVariable Long externalInvoiceId) throws Exception {
        System.out.println("reached upload image");
        invoice invoice = invoiceRepo.findByexid(externalInvoiceId);
        System.out.println(invoice);

        Map<String, Object> json = new HashMap();
        long id =fileLocationService.saveImage(image.getBytes(), image.getOriginalFilename(),invoice);
        json.put("id",id);
        System.out.println(json);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("acces token", AmrTrainingApplication.token);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(json);
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    FileSystemResource downloadImage(@PathVariable Long imageId) throws Exception {
        return fileLocationService.find(imageId);
    }

    @PostMapping("/uoloadPDF/{externalInvoiceId}")
    ResponseEntity<Map<String, Object>> uploadPdf(@RequestParam MultipartFile pdf, @PathVariable Long externalInvoiceId) throws Exception {
        System.out.println("reached upload Pdf");
        invoice invoice = invoiceRepo.findByexid(externalInvoiceId);
        System.out.println(invoice);
        Map<String, Object> json = new HashMap();
        long id =fileLocationService.savePDF(pdf.getBytes(), pdf.getOriginalFilename(),invoice);
        System.out.println("idddddddddddddddd\t"+id);
        json.put("id",id);
        System.out.println(json);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("acces token", AmrTrainingApplication.token);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(json);
    }

    @GetMapping(value = "/pdf/{pdfId}", produces = MediaType.APPLICATION_PDF_VALUE)
    FileSystemResource downloadPDF(@PathVariable Long pdfId) throws Exception {
        return fileLocationService.find(pdfId);
    }




}
