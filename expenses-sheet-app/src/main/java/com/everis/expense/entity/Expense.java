package com.everis.expense.entity;

import com.everis.common.entity.AbstractEntity;
import com.everis.expenseType.entity.ExpenseType;
import com.everis.home.entity.Home;
import com.everis.login.entity.User;

import javax.persistence.*;

@Entity
@Table(name = Expense.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = Expense.GET_ALL, query = "SELECT e FROM Expense e")
})
public class Expense extends AbstractEntity {

    public static final String TABLE_NAME = "expenses";
    public static final String GET_ALL = "com.everis.expense.getAll";

    @Column
    private String description;
    @Column
    private Float value;
    @ManyToOne
    private ExpenseType expenseType;
    @ManyToOne
    private Home home;
    @ManyToOne
    private User user;

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
