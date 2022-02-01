package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.Model.item;
import com.trainingHarri.com.amrTraining.Repositries.itemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class itemService {
    @Autowired
    itemRepo itemRepo;
    public void addItem(item item) {
        itemRepo.save(item);
    }
}
