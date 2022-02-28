package com.trainingHarri.com.amrTraining.exceptions;

public class UsedNameExeption extends RuntimeException  {
    private static final long serialVersionUID = -7806029002430564887L;

    private String message;
    public UsedNameExeption(String message) {
        this.message=message;
    }

    public UsedNameExeption() {
    }

    public void setErrorMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return message;
    }


}