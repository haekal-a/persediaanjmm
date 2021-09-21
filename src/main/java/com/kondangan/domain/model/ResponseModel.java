package com.kondangan.domain.model;

import java.io.Serializable;

public class ResponseModel<Object> implements Serializable {

    private Integer code;
    private String title;
    private String message;
    private Object object;

    public ResponseModel() {
    }

    public ResponseModel(String title) {
        this.code = 1;
        this.title = title;
        this.message = "Sukses";
    }

    public ResponseModel(Integer code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
