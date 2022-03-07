package com.trainingHarri.com.amrTraining.DTOs;

import com.trainingHarri.com.amrTraining.Model.attachment;
import com.trainingHarri.com.amrTraining.Model.sUser;

import java.util.Date;
import java.util.List;

public class invoiceDto {


    private long invoiceId ;
    private long userId ;
    private Date dateOfCreation;
    private double amount;
    private Long externalInvoiceId;
    private sUser user;
    private List<attachment> attachmentList;
    private List<Long> itemss;
    private List<Long> quantity;

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public sUser getUser() {
        return user;
    }

    public void setUser(sUser user) {
        this.user = user;
    }

    public List<attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<Long> getItems() {
        return itemss;
    }

    public void setItems(List<Long> items) {
        itemss = items;
    }

    public List<Long> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Long> quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "invoiceDto{" +
                "invoiceId=" + invoiceId +
                ", userId=" + userId +
                ", dateOfCreation=" + dateOfCreation +
                ", amount=" + amount +
                ", externalInvoiceId=" + externalInvoiceId +
                ", user=" + user +
                ", attachmentList=" + attachmentList +
                ", items=" + itemss +
                ", quantity=" + quantity +
                '}';
    }
}
