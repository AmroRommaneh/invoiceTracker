package com.trainingHarri.com.amrTraining.DTOs;

public class attachmentDto {



    private long Id;
    private com.trainingHarri.com.amrTraining.attachmentType attachmentType;
    private long externalInvoiceId;
    private String URL;

    public attachmentDto( com.trainingHarri.com.amrTraining.attachmentType attachmentType, long invoiceId, String URL) {
        this.attachmentType = attachmentType;
        this.externalInvoiceId = invoiceId;
        this.URL = URL;
    }

    public com.trainingHarri.com.amrTraining.attachmentType getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(com.trainingHarri.com.amrTraining.attachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }

    public long getExternalInvoiceId() {
        return externalInvoiceId;
    }

    public void setExternalInvoiceId(long externalInvoiceId) {
        this.externalInvoiceId = externalInvoiceId;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
