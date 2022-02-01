package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import com.trainingHarri.com.amrTraining.Repositries.roleRepo;
import org.springframework.stereotype.Service;

@Service
public class roleService {

    @Autowired

    roleRepo roleRepo;
    public void addRole(Role role) {
        roleRepo.save(role);
    }

}
