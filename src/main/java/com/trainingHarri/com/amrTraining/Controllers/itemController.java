package com.trainingHarri.com.amrTraining.Controllers;


import com.trainingHarri.com.amrTraining.DTOs.itemDto;
import com.trainingHarri.com.amrTraining.Model.item;
import com.trainingHarri.com.amrTraining.Services.itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/Item")
@CrossOrigin

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


    @GetMapping(path = "/getItems")
    public ResponseEntity<List<item>> getItems() {
        //   Pageable firstPageWithTenElements = PageRequest.of(0, 10, Sort.by("invoiceId").descending());

//System.out.println("weewn ward"+invoiceRepoPa.findAll(page));
        return new ResponseEntity<>(itemservice.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/getItems/{id}")
    public ResponseEntity<item> getItemsById(@PathVariable long id) {
        //   Pageable firstPageWithTenElements = PageRequest.of(0, 10, Sort.by("invoiceId").descending());

//System.out.println("weewn ward"+invoiceRepoPa.findAll(page));
        return new ResponseEntity<>(itemservice.find(id), HttpStatus.OK);
    }


    @GetMapping(path = "/getDiff/{id}")
    public ResponseEntity<List<Object>> getItemsId(@PathVariable long id) {
        //   Pageable firstPageWithTenElements = PageRequest.of(0, 10, Sort.by("invoiceId").descending());

//System.out.println("weewn ward"+invoiceRepoPa.findAll(page));
        return new ResponseEntity<>(itemservice.dif(id), HttpStatus.OK);
    }

    @GetMapping(path = "/getItemsNames")
    public ResponseEntity<List<String>> getItemsNames() {
        //   Pageable firstPageWithTenElements = PageRequest.of(0, 10, Sort.by("invoiceId").descending());

//System.out.println("weewn ward"+invoiceRepoPa.findAll(page));
        return new ResponseEntity<>(itemservice.findAllNames(), HttpStatus.OK);
    }

}
