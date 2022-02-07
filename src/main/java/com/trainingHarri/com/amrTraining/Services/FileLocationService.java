package com.trainingHarri.com.amrTraining.Services;


import com.trainingHarri.com.amrTraining.Repositries.FileSystemRepository;
import com.trainingHarri.com.amrTraining.Repositries.attachmentRepo;
import com.trainingHarri.com.amrTraining.attachmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.trainingHarri.com.amrTraining.Model.*;

@Service
public class FileLocationService {

    @Autowired
    FileSystemRepository fileSystemRepository;
    @Autowired
    attachmentRepo attachmentRepo;

    public long saveImage(byte[] bytes, String imageName, invoice invoice) throws Exception {
        String location = fileSystemRepository.save(bytes, imageName);
        attachment x = new attachment(imageName, location);
        x.setInvoice(invoice);
        x.setAttachmentType(attachmentType.IMAGE);
        attachmentRepo.save(x);
        System.out.println("iddddd  from service  " + x.getId());

        long id = x.getId();
        return id;
    }

    public long savePDF(byte[] bytes, String pdfName ,invoice invoice) throws Exception {
        String location = fileSystemRepository.save(bytes, pdfName);
        attachment x = new attachment(pdfName, location);
        x.setInvoice(invoice);
        x.setAttachmentType(attachmentType.PDF);
        attachmentRepo.save(x);
        System.out.println("iddddd  from service  " + x.getId());

        long id = x.getId();
        return id;
    }

    public FileSystemResource find(Long fileId) {
        attachment file = attachmentRepo.findById(fileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        System.out.println("location is " + file.getLocation());
        return fileSystemRepository.findInFileSystem(file.getLocation());
    }


}