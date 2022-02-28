package com.trainingHarri.com.amrTraining.Controllers;

import com.trainingHarri.com.amrTraining.AmrTrainingApplication;
import com.trainingHarri.com.amrTraining.Model.attachment;
import com.trainingHarri.com.amrTraining.Model.invoice;
import com.trainingHarri.com.amrTraining.Repositries.attachmentRepo;
import com.trainingHarri.com.amrTraining.Services.FileLocationService;
import com.trainingHarri.com.amrTraining.Services.attachmentService;
import com.trainingHarri.com.amrTraining.exceptions.customExeption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
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

        long id;
        Map<String, Object> json = new HashMap();
        try{
        id =fileLocationService.saveImage(image.getBytes(), image.getOriginalFilename(),invoice);
            json.put("id",id);
        }
        catch (customExeption e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error","NO INVOICE HAS THIS EXTERNAL ID");
            return ResponseEntity.badRequest().headers(responseHeaders).body(null);

        }
        System.out.println(json);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("acces token", AmrTrainingApplication.token);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(json);
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<FileSystemResource> downloadImage(@PathVariable Long imageId) throws Exception {
        return new ResponseEntity<>(fileLocationService.find(imageId), HttpStatus.OK);
    }

    @PostMapping("/uoloadPDF/{externalInvoiceId}")
    ResponseEntity<Map<String, Object>> uploadPdf(@RequestParam MultipartFile pdf, @PathVariable Long externalInvoiceId) throws Exception {
        System.out.println("reached upload Pdf");
        invoice invoice = invoiceRepo.findByexid(externalInvoiceId);
        System.out.println(invoice);
        Map<String, Object> json = new HashMap();
        long id;
        try{
            id=fileLocationService.savePDF(pdf.getBytes(), pdf.getOriginalFilename(),invoice);

            json.put("id",id);
        }
        catch (customExeption e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error","NO INVOICE HAS THIS EXTERNAL ID");
            return ResponseEntity.badRequest().headers(responseHeaders).body(null);

        }
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
    ResponseEntity<FileSystemResource> downloadPDF(@PathVariable Long pdfId) throws Exception {
        return new ResponseEntity<>(fileLocationService.find(pdfId),HttpStatus.OK);
    }

    @PostMapping("/uoloadWEB/{externalInvoiceId}")
    ResponseEntity<Map<String, Object>> uploadWeb(@RequestParam MultipartFile web, @PathVariable Long externalInvoiceId) throws Exception {
        System.out.println("reached upload Pdf");
        invoice invoice = invoiceRepo.findByexid(externalInvoiceId);
        System.out.println(invoice);
        Map<String, Object> json = new HashMap();
        long id;
        try{
            id=fileLocationService.saveWEB(web.getBytes(), web.getOriginalFilename(),invoice);
            json.put("id",id);
        }
        catch (customExeption e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error","NO INVOICE HAS THIS EXTERNAL ID");
            return ResponseEntity.badRequest().headers(responseHeaders).body(null);

        }
        System.out.println("idddddddddddddddd\t"+id);
        json.put("id",id);
        System.out.println(json);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("acces token", AmrTrainingApplication.token);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(json);
    }


    @GetMapping(value = "/getAttachment/{atId}", produces = MediaType.ALL_VALUE)
    ResponseEntity<FileSystemResource> downloadAttach(@PathVariable Long atId) throws Exception {
        return new ResponseEntity<>(fileLocationService.find(atId),HttpStatus.OK);
    }

    @GetMapping(value = "/getAllAttachment/{inv}")
    ResponseEntity<List<attachment>> getAttachments(@PathVariable Long inv) throws Exception {
        return new ResponseEntity<>(attachmentService.find(inv),HttpStatus.OK);
    }




}
