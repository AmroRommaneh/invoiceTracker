package com.trainingHarri.com.amrTraining.Controllers;

import com.trainingHarri.com.amrTraining.DTOs.attachmentDto;
import com.trainingHarri.com.amrTraining.Model.attachment;
import com.trainingHarri.com.amrTraining.Services.attachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class attachmentController {

    @Autowired
    attachmentService attachmentService;

    @PostMapping(path = "/addAttachment")
    public ResponseEntity<String> addAttachment(@RequestBody attachmentDto attachmentdto) {
        attachment attach = new attachment();

        attach.setAttachmentType(attachmentdto.getAttachmentType());
        attach.setURL(attachmentdto.getURL());
        attach.setInvoice(attachmentdto.getInvoice());

        return new ResponseEntity<>("the attachment has been added", HttpStatus.OK);
    }







}
