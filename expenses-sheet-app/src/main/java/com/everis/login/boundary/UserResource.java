package com.everis.login.boundary;

import com.everis.common.boundary.Logged;
import com.everis.login.entity.User;
import com.everis.security.boundary.BasicAuthentication;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
@Produces({"application/json"})
public class UserResource {

    @Inject
    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @BasicAuthentication
    public User createUser(User user) {
        return this.loginService.createUser(user);
    }

    @GET
    public List<User> getUsers() {
        return this.loginService.getUsers();
    }

}
