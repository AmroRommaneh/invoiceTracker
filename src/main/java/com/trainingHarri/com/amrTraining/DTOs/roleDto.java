package com.trainingHarri.com.amrTraining.DTOs;

import com.trainingHarri.com.amrTraining.Model.sUser;

import java.util.List;

public class roleDto {

    private int id;
    private com.trainingHarri.com.amrTraining.roleName roleName;
    private String discription;
    private List<sUser> users;

    public com.trainingHarri.com.amrTraining.roleName getRoleName() {
        return roleName;
    }

    public void setRoleName(com.trainingHarri.com.amrTraining.roleName roleName) {
        this.roleName = roleName;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public List<sUser> getUsers() {
        return users;
    }

    public void setUsers(List<sUser> users) {
        this.users = users;
    }
}
