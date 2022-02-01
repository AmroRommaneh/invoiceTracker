package com.trainingHarri.com.amrTraining.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "Invoice")
public class invoice {
@Id
    @GeneratedValue
    @Column(name = "Id")
    private long invoiceId ;
    @Column(name = "DateOfCreation")
    private Date dateOfCreation;
    @Column(name = "amount")
    private double amount;
   @Column(name = "externalInvoiceId" ,unique = true)
    private Long externalInvoiceId;

    public invoice() {
    }

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy = "invoice")
    private List<attachment> attachmentList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "invoiceItem",
            joinColumns = @JoinColumn(name = "invoiceId", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "itemId", referencedColumnName = "Id"))
    private List<item> Items;


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

    public List<attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<item> getItems() {
        return Items;
    }

    public void setItems(List<item> items) {
        Items = items;
    }
}
