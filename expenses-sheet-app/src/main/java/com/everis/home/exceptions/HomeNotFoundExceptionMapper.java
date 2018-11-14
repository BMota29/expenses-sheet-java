package com.everis.home.exceptions;

import com.everis.common.dto.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HomeNotFoundExceptionMapper implements ExceptionMapper<HomeNotFoundException> {
    @Override
    public Response toResponse(HomeNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage(Response.Status.NOT_FOUND, exception.getMessage()))
                .build();
    }
}
