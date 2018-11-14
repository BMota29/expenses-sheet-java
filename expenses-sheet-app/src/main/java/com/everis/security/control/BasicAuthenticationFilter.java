package com.everis.security.control;

import com.everis.login.boundary.LoginService;
import com.everis.login.entity.User;
import com.everis.login.exceptions.AuthUserNotFound;
import com.everis.security.boundary.BasicAuthentication;
import com.everis.security.exceptions.UnauthorizedException;
import org.eclipse.persistence.internal.oxm.conversion.Base64;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.NoResultException;
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
import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;
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

        if(!isAuthorized()) {
            LOG.warning("User or Password don't match");
            throw new UnauthorizedException("User or Password don't match");
        }

    }

    /**
     * Checks if anotation is on method ou class.
     * @return
     */
    private boolean isAuthorized() {
        HttpSession session = servletRequest.getSession();

        Method resourceMethod = resourceInfo.getResourceMethod();
        BasicAuthentication basicAuthentication = resourceMethod.getDeclaredAnnotation(BasicAuthentication.class);

        if(basicAuthentication == null) {
            Class<?> resourceClass = resourceInfo.getResourceClass();
            basicAuthentication = resourceClass.getDeclaredAnnotation(BasicAuthentication.class);
        }

        return isHttpBasicAuthorized(headers, basicAuthentication);
    }

    /**
     * checks if the user passed on header is a valid user
     * @param headers
     * @param basicAuthentication
     * @return
     */
    private boolean isHttpBasicAuthorized(HttpHeaders headers, BasicAuthentication basicAuthentication) {
        User user = getHttpAuthorization(headers);
        if (user == null)
            return false;
        User userValidated = loginService.validate(user);
        return userValidated != null;
    }

    /**
     * Checks if header with authorization exists, if exists creates a user from the header info
     * @param headers
     * @return
     */
    private User getHttpAuthorization(HttpHeaders headers) {
        List<String> header = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);

        if(header == null || header.isEmpty()) {
            return null;
        }

        String authorization = header.get(0);
        authorization = authorization.substring("Basic ".length());

        final String usernameAndPassword = new String(Base64.base64Decode(authorization.getBytes()));
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");

        if(tokenizer.countTokens() < 2)
            return null;
        User user = new User();
        user.setUsername(tokenizer.nextToken());
        user.setPassword(tokenizer.nextToken());
        return user;
    }

}
