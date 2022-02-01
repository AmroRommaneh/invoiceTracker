package com.trainingHarri.com.amrTraining.DTOs;
import com.trainingHarri.com.amrTraining.Model.invoice;

public class attachmentDto {



    private long Id;
    private com.trainingHarri.com.amrTraining.attachmentType attachmentType;
    private com.trainingHarri.com.amrTraining.Model.invoice invoice;
    private String URL;

    public attachmentDto( com.trainingHarri.com.amrTraining.attachmentType attachmentType, invoice invoiceId, String URL) {
        this.attachmentType = attachmentType;
        this.invoice = invoiceId;
        this.URL = URL;
    }

    public com.trainingHarri.com.amrTraining.attachmentType getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(com.trainingHarri.com.amrTraining.attachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }

    public com.trainingHarri.com.amrTraining.Model.invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(invoice invoice) {
        this.invoice = invoice;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
