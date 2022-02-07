package com.trainingHarri.com.amrTraining.Model;

import com.trainingHarri.com.amrTraining.Gender;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="User")
public class sUser {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long userid ;
    @Column(name = "email" ,unique = true)
    private String email;
    @Column(name = "name",unique = true)
    private String name;
    @Column(name = "password")
    private  String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "phoneNumber",unique = true)
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "userRole",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "Id"))
    private List<Role> Roles;

    @OneToMany(mappedBy = "user")
    private List<invoice> invoices;

    public sUser(String email, String name, String password, Gender gender, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public sUser() {
    }

    public Long getUserid() {
        return userid;
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

    public List<Role> getRoles() {
        return Roles;
    }

    public void setRoles(List<Role> roles) {
        Roles = roles;
    }


}
