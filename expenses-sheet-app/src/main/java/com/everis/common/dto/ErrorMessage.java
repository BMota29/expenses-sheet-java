package com.everis.common.dto;

import javax.ws.rs.core.Response;

public class ErrorMessage {
    private Response.Status response;
    private String message;

    public ErrorMessage(Response.Status response, String message) {
        this.response = response;
        this.message = message;
    }
}
