package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.Model.attachment;
import com.trainingHarri.com.amrTraining.Repositries.attachmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class attachmentService {

    @Autowired
    attachmentRepo attachmentRepo;

    public void addInvoice(attachment attach) {
        attachmentRepo.save(attach);
    }

    public List<attachment> find(Long inv) {

        return attachmentRepo.findAttachmentsByInvoiceID(inv);
    }
}
