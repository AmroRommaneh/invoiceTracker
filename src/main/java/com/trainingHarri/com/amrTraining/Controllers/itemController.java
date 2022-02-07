package com.trainingHarri.com.amrTraining.Controllers;


import com.trainingHarri.com.amrTraining.DTOs.itemDto;
import com.trainingHarri.com.amrTraining.Model.item;
import com.trainingHarri.com.amrTraining.Services.itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/Item")
public class itemController {


    @Autowired
    itemService itemservice;
    @PostMapping(path = "/addItem")
    public ResponseEntity<String> addItem(@RequestBody itemDto itemdto) {
        item item = new item();
        System.out.println("reeeched1");

        item.setName(itemdto.getName());
        item.setPrice(itemdto.getPrice());
        item.setQuantity(itemdto.getQuantity());
        itemservice.addItem(item);
        return new ResponseEntity<>("the item has been added", HttpStatus.OK);
    }



}
