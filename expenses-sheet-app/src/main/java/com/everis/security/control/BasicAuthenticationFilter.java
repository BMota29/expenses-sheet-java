package com.everis.security.control;

import com.everis.login.boundary.LoginService;
import com.everis.security.boundary.BasicAuthentication;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
@BasicAuthentication
public class BasicAuthenticationFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(BasicAuthenticationFilter.class.getName());

    @Inject
    private LoginService loginService;

    @Context
    private HttpServletRequest servletRequest;

    @Context
    private HttpHeaders headers;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        final Response err = Response.status(Response.Status.UNAUTHORIZED).build();

        if(!isAuthorized())
            requestContext.abortWith(err);

    }

    private boolean isAuthorized() {
        HttpSession session = servletRequest.getSession();


        return true;
    }

}
