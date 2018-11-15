package com.everis.paycheckType.boundary;

import com.everis.paycheckType.entity.PaycheckType;
import com.everis.security.boundary.BasicAuthentication;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("paychecks/types")
@BasicAuthentication
@Produces(MediaType.APPLICATION_JSON)
public class PaycheckTypeResource {

    @Inject
    private PaycheckTypeService paycheckTypeService;

    @GET
    public List<PaycheckType> getAll() {
        return this.paycheckTypeService.getAllPaycheckTypes();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public PaycheckType createPaycheckType(PaycheckType paycheckType) {
        return this.paycheckTypeService.createPaycheckType(paycheckType);
    }

}
