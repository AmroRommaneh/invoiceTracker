package com.trainingHarri.com.amrTraining.DTOs;

import com.trainingHarri.com.amrTraining.Model.User;

import java.util.List;

public class roleDto {

    private int id;
    private com.trainingHarri.com.amrTraining.roleName roleName;
    private String discription;
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
