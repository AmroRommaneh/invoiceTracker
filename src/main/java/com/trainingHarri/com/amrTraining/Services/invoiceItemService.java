package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.Model.invoiceItem;
import com.trainingHarri.com.amrTraining.Model.item;
import com.trainingHarri.com.amrTraining.Repositries.invoiceItemRepo;
import com.trainingHarri.com.amrTraining.exceptions.customExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class invoiceItemService {

    @Autowired
    invoiceItemRepo invoiceItemRepo;
    @Autowired
    com.trainingHarri.com.amrTraining.Repositries.itemRepo itemRepo;

    public void saveInvoiceItem(long invoiceId, long itemId){
        item x = itemRepo.find(itemId);
        if(x==null){
            throw new customExeption("NO ITEM EXIST WITH THIS ID  "+itemId);
        }else {
            x.setQuantity(x.getQuantity()-1.0);
        invoiceItem invoiceItem =new invoiceItem();
        invoiceItem.setInvoiceId(invoiceId);
        invoiceItem.setItemId(itemId);
            itemRepo.save(x);
        invoiceItemRepo.save(invoiceItem);
    }}

    public void checkItem(long itemId){
        item x = itemRepo.find(itemId);
        if(x==null){
            throw new customExeption("NO ITEM EXIST WITH THIS ID  "+itemId);}
}}
