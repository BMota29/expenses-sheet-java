package com.everis.expense.boundary;

import com.everis.expense.entity.Expense;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("expenses")
public class ExpenseResource {


    @Inject
    private ExpenseService expenseService;

    @GET
    public List<Expense> getAllExpenses() {
        return this.expenseService.getAllExpenses();
    }

    @GET
    @Path("{id}")
    public Expense getExpenseById( @PathParam("id") long id ) {
        return this.expenseService.getExpenseById(id);
    }

}
