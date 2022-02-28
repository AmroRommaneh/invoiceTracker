package com.trainingHarri.com.amrTraining.exceptions;

public class UsedEmailExeption extends RuntimeException  {
    private static final long serialVersionUID = -7806029002430564887L;

    private String message;
    public UsedEmailExeption(String message) {
        this.message=message;
    }

    public UsedEmailExeption() {
    }

    public void setErrorMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return message;
    }


}