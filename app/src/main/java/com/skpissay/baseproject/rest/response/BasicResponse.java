package com.skpissay.baseproject.rest.response;

/**
 * Created by skpissay on 25/06/18.
 */

public class BasicResponse {
    private boolean success;
    private RestError error;

    public BasicResponse(boolean success) {
        this.success = success;
    }

    public BasicResponse() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public BasicResponse(boolean success, RestError error) {
        this.success = success;
        this.error = error;
    }

    public RestError getError() {
        return this.error;
    }

    public void setError(RestError error) {
        this.error = error;
    }
}
