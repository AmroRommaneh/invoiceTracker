package com.trainingHarri.com.amrTraining.Model;


import com.trainingHarri.com.amrTraining.roleName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Role")
public class Role {
    @GeneratedValue()
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private roleName roleName;

    @Column(name = "discription")
    private String discription;

    @ManyToMany(mappedBy = "Roles")
    private List<sUser> users;

    public Role(com.trainingHarri.com.amrTraining.roleName roleName, String discription) {
        this.roleName = roleName;
        this.discription = discription;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

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
