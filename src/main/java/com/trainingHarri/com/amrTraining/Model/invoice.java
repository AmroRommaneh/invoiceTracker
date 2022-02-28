package com.trainingHarri.com.amrTraining.Model;

import com.trainingHarri.com.amrTraining.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "Invoice")
public class invoice {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long invoiceId ;
    @Column(name = "DateOfCreation")
    private Date dateOfCreation;
    @Column(name = "amount")
    private double amount;
   @Column(name = "externalInvoiceId" ,unique = true)
    private Long externalInvoiceId;
    @Enumerated(EnumType.STRING)
   private Status status;
    @Column(name = "DateOfDeletion")
    private Date dateOfDeletion;

    public invoice() {
    }

    @ManyToOne
    @JoinColumn(name="userId")
    private sUser user;

    @OneToMany(mappedBy = "invoice")
    private List<attachment> attachmentList;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "invoiceItem",
//            joinColumns = @JoinColumn(name = "invoiceId", referencedColumnName = "Id"),
//            inverseJoinColumns = @JoinColumn(name = "itemId", referencedColumnName = "Id"))
//    private List<item> Items;


    public invoice(Date dateOfCreation, double amount, Long externalInvoiceId) {
        this.dateOfCreation = dateOfCreation;
        this.amount = amount;
        this.externalInvoiceId = externalInvoiceId;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getExternalInvoiceId() {
        return externalInvoiceId;
    }

    public void setExternalInvoiceId(Long externalInvoiceId) {
        this.externalInvoiceId = externalInvoiceId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public List<Long> getAttachmentList() {
        List <Long> x =new ArrayList<Long>() ;
        for (int i =0; i< attachmentList.size();i++){
            x.add(attachmentList.get(i).getId());
        }

        return x;
    }

    public void setAttachmentList(List<attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

//    public List<item> getItems() {
//        return Items;
//    }
//
//    public void setItems(List<item> items) {
//        Items = items;
//    }


    public sUser getUser() {
        return user;
    }

    public void setUser(sUser user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateOfDeletion() {
        return dateOfDeletion;
    }

    public void setDateOfDeletion(Date dateOfDeletion) {
        this.dateOfDeletion = dateOfDeletion;
    }

    @Override
    public String toString() {
        return "invoice{" +
                "invoiceId=" + invoiceId +
                ", dateOfCreation=" + dateOfCreation +
                ", amount=" + amount +
                ", externalInvoiceId=" + externalInvoiceId +
                ", user=" + user +
                ", attachmentList=" + attachmentList +
                '}';
    }
}
