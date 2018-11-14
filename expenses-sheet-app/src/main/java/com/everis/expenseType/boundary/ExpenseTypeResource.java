package com.everis.expenseType.boundary;

import com.everis.expenseType.entity.ExpenseType;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("expenses/types")
public class ExpenseTypeResource {

    @Inject
    private ExpenseTypeService expenseTypeService;

    @GET
    public List<ExpenseType> getAll() {
        return this.expenseTypeService.getAllExpenseType();
    }

}
