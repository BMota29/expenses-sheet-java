package com.everis.home.boundary;

import com.everis.home.entity.Home;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("home")
@Produces(MediaType.APPLICATION_JSON)
public class HomeResources {

    @Inject
    private HomeService homeService;

    @GET
    public List<Home> getAllHomes() {
        return this.homeService.getAllHomes();
    }

    @GET
    @Path("{id}")
    public Home getHomeById(@PathParam("id") long id) {
        return this.homeService.getHomeById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Home createHome(Home home) {
        return this.homeService.createHome(home);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Home updateHome(Home home) {
        return this.homeService.updateHome(home);
    }

    @DELETE
    @Path("{id}")
    public Home removeHome(@PathParam("id") long id) {
        return this.homeService.removeHome(id);
    }

}
