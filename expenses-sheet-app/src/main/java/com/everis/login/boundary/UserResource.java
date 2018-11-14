package com.everis.login.boundary;

import com.everis.common.boundary.Logged;
import com.everis.login.entity.User;
import com.everis.security.boundary.BasicAuthentication;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
@Logged
@Produces({"application/json"})
public class UserResource {

    @Inject
    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
        return this.loginService.createUser(user);
    }

    @GET
    @BasicAuthentication
    public List<User> getUsers() {
        return this.loginService.getUsers();
    }

    @GET
    @Path("{id}")
    @BasicAuthentication
    public User getUserById(@PathParam("id") Long id ) {
        return this.loginService.getUserById(id);
    }

    @GET
    @Path("username/{username}")
    @BasicAuthentication
    public User getUserByUsername(@PathParam("username") String username) {
        return this.loginService.getUserByUsername(username, false);
    }

    @GET
    @Path("email/{email}")
    @BasicAuthentication
    public User getUserByEmail(@PathParam("email") String email) {
        return this.loginService.getUserByEmail(email);
    }

    @DELETE
    @Path("{id}")
    @BasicAuthentication
    public User removeUser(@PathParam("id") long id) {
        return this.loginService.removeUser(id);
    }


}
