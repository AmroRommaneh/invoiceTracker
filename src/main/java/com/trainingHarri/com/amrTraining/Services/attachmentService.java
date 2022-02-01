package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.Model.attachment;
import com.trainingHarri.com.amrTraining.Repositries.attachmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class attachmentService {

    @Autowired
    attachmentRepo attachmentRepo;

    public void addInvoice(attachment attach) {
        attachmentRepo.save(attach);
    }

}
