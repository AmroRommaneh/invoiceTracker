package com.trainingHarri.com.amrTraining.DTOs;

import com.trainingHarri.com.amrTraining.Gender;
import com.trainingHarri.com.amrTraining.Model.Role;

import java.util.List;

public class userDto {

    private long userid ;
    private String email;
    private String name;
    private  String password;
    private Gender gender;
    private String phoneNumber;
    private List<Role> Roles;

    public userDto(long userid, String email, String name, String password, Gender gender, String phoneNumber, List<Role> roles) {
        this.userid = userid;
        this.email = email;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        Roles = roles;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public List<Role> getRoles() {
        return Roles;
    }

    public void setRoles(List<Role> roles) {
        Roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public userDto() {
    }

    @Override
    public String toString() {
        return "userDto{" +
                "userid=" + userid +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", Roles=" + Roles +
                '}';
    }
}
