package com.everis.expenseType.entity;

import com.everis.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.QueryParam;

@Entity
@Table(name = ExpenseType.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = ExpenseType.GET_ALL, query = "SELECT e FROM ExpenseType e")
})
public class ExpenseType extends AbstractEntity {

    public static final String TABLE_NAME = "expenseTypes";
    public static final String GET_ALL = "com.everis.expenseType.getAll";

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExpenseType() {
        super();
    }

    public ExpenseType(String description) {
        this.description = description;
    }
}
