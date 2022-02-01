package com.trainingHarri.com.amrTraining.Model;

import com.trainingHarri.com.amrTraining.attachmentType;

import javax.persistence.*;
@Entity
@Table(name = "Attachment")
public class attachment {
    @Id
    @Column(name = "attachId")
    @GeneratedValue
    private long Id;
    @Column(name = "attachmentType")
    private attachmentType attachmentType;

    @Column(name = "URL")
    private String URL;


    public long getId() {
        return Id;
    }

    @ManyToOne
    @JoinColumn(name="invoiceId")
    private invoice invoice;

    public attachment(com.trainingHarri.com.amrTraining.attachmentType attachmentType, String URL, com.trainingHarri.com.amrTraining.Model.invoice invoice) {
        this.attachmentType = attachmentType;
        this.URL = URL;
        this.invoice = invoice;
    }

    public attachment() {
    }

    public attachmentType getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(com.trainingHarri.com.amrTraining.attachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }


    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public com.trainingHarri.com.amrTraining.Model.invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(com.trainingHarri.com.amrTraining.Model.invoice invoice) {
        this.invoice = invoice;
    }
}
