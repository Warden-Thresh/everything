package com.warden.common.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**Gson
 * @author YangJiaYing
 */

public class Response<T> implements Serializable {

    private int status = 200;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public T getData() {
        return data;
    }

    public Response setData(T data) {
        this.data = data;
        return this;
    }

    public Response() {
    }

    public static <T> Response success(T data) {
        Response response = new Response();
        response.data = data;
        response.status = 200;
        return response;
    }

    public static<T> Response success() {
        Response response = new Response();
        response.status = 200;
        return response;
    }

    public static<T> Response failure(int status,String errorMessage) {
        Response response = new Response();
        response.message = errorMessage;
        response.status = status;
        return response;
    }

    public Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public Response setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

}
