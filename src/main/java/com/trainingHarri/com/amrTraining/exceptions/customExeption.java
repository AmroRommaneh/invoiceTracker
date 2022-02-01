package com.trainingHarri.com.amrTraining.exceptions;

public class customExeption extends RuntimeException  {
    private static final long serialVersionUID = -7806029002430564887L;

    private String message;
    public customExeption(String message) {
this.message=message;
    }

    public customExeption() {
    }

    public void setErrorMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return message;
    }


}