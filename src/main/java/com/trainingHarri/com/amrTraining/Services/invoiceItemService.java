package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.Model.invoiceItem;
import com.trainingHarri.com.amrTraining.Repositries.invoiceItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class invoiceItemService {

    @Autowired
    invoiceItemRepo invoiceItemRepo;

    public void saveInvoiceItem(long invoiceId,long itemId ){
        invoiceItem invoiceItem =new invoiceItem();
        invoiceItem.setInvoiceId(invoiceId);
        invoiceItem.setItemId(itemId);

        invoiceItemRepo.save(invoiceItem);
    }
}
