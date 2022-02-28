package com.trainingHarri.com.amrTraining.Controllers;

import com.trainingHarri.com.amrTraining.DTOs.roleDto;
import com.trainingHarri.com.amrTraining.Model.Role;
import com.trainingHarri.com.amrTraining.Services.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller


public class RoleController {

    @Autowired
    roleService roleService;


    @PostMapping(path = "/addRole")
    public ResponseEntity<String> addRole(@RequestBody roleDto roledto) {
        Role role = new Role();
        System.out.println("reeeched1");
        role.setRoleName(roledto.getRoleName());
        role.setDiscription(roledto.getDiscription());

        roleService.addRole(role);

        return new ResponseEntity<>("the item has been added", HttpStatus.OK);
    }
}
