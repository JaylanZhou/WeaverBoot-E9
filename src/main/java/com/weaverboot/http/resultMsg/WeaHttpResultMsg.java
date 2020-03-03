package com.weaverboot.http.resultMsg;

public class WeaHttpResultMsg {

    private String data;

    private int httpStatus;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

}
