package com.everis.expenseType.boundary;

import com.everis.expenseType.entity.ExpenseType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ExpenseTypeService {

    @PersistenceContext
    private EntityManager entityManager;

    public ExpenseType createExpenseType(ExpenseType expenseType) {
        this.entityManager.persist(expenseType);
        this.entityManager.flush();
        return expenseType;
    }

    public List<ExpenseType> getAllExpenseType() {
        Query query = this.entityManager.createNamedQuery(ExpenseType.GET_ALL, ExpenseType.class);
        return query.getResultList();
    }
}
