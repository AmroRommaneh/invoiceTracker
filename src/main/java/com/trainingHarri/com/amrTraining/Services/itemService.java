package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.Model.invoiceItem;
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

    public List<invoiceItem> dif(long id) {
        List<invoiceItem> y=invoiceItemRepo.findDif(id);


for (int i =0 ; i<y.size();i++){
    System.out.println(y.get(i).toString());
}

        return y ;
    }


    public List<String> findAllNames() {

        return itemRepo.findAllNames();


    }
}
