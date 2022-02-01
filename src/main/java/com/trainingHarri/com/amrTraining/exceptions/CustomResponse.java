package com.trainingHarri.com.amrTraining.exceptions;

public class CustomResponse {

        private String error;

        public CustomResponse() {

        }

        public CustomResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }


}
