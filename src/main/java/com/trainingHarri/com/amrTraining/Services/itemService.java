package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.Model.item;
import com.trainingHarri.com.amrTraining.Repositries.itemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class itemService {
    @Autowired
    itemRepo itemRepo;
    @Autowired
    com.trainingHarri.com.amrTraining.Repositries.invoiceItemRepo invoiceItemRepo;
    public void addItem(item item) {
        itemRepo.save(item);
    }

    public List<item> findAll() {

       return itemRepo.findAll();


    }

    public item find(long id) {
        return itemRepo.find(id);

    }

    public List<Object> dif(long id) {
        List<Object> y=invoiceItemRepo.findDif(id);




        return y ;
    }


    public List<String> findAllNames() {

        return itemRepo.findAllNames();


    }
}
