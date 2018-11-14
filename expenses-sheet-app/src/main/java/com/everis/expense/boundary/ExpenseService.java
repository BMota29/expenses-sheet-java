package com.everis.expense.boundary;


import com.everis.expense.entity.Expense;
import com.everis.expense.exceptions.ExpenseNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ExpenseService {

    @PersistenceContext
    private EntityManager entityManager;

    public Expense createExpense(Expense expense) {
        this.entityManager.persist(expense);
        this.entityManager.flush();
        return expense;
    }

    public List<Expense> getAllExpenses() {
        Query query = this.entityManager.createNamedQuery(Expense.GET_ALL);
        List<Expense> expenses = query.getResultList();
        expenses.forEach(expense -> {
            this.entityManager.detach(expense.getUser());
            expense.getUser().setSalt(null);
        });
        return expenses;
    }

    public Expense getExpenseById(long id) {
        Expense expense = this.entityManager.find(Expense.class, id);
        if (expense == null)
            throw new ExpenseNotFoundException();
        this.entityManager.detach(expense.getUser());
        expense.getUser().setSalt(null);
        return expense;
    }
}
