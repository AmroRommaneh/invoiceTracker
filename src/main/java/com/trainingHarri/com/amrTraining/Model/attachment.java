package com.trainingHarri.com.amrTraining.Model;

import com.trainingHarri.com.amrTraining.attachmentType;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "Attachment")
public class attachment {
    @Id
    @Column(name = "attachId")
    @GeneratedValue
    private long Id;
    @Enumerated(EnumType.STRING)
    private attachmentType attachmentType;



    @Lob
    File content;

    @Column(name = "attachmentName")
    String name;
@Column(name = "location")
    String location;


    public long getId() {
        return Id;
    }

    @ManyToOne
    @JoinColumn(name="invoiceId")
    private invoice invoice;

    public attachment(com.trainingHarri.com.amrTraining.attachmentType attachmentType, File content, String name, String location, com.trainingHarri.com.amrTraining.Model.invoice invoice) {
        this.attachmentType = attachmentType;
        this.content = content;
        this.name = name;
        this.location = location;
        this.invoice = invoice;
    }

    public attachment(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public attachment() {
    }

    public attachmentType getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(com.trainingHarri.com.amrTraining.attachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }



    public com.trainingHarri.com.amrTraining.Model.invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(com.trainingHarri.com.amrTraining.Model.invoice invoice) {
        this.invoice = invoice;
    }

    public File getContent() {
        return content;
    }

    public void setContent(File content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
