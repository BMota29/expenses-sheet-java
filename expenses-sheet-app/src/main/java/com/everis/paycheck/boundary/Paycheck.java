package com.everis.paycheck.boundary;

import com.everis.common.entity.AbstractEntity;
import com.everis.login.entity.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = Paycheck.TABLE_NAME)
public class Paycheck extends AbstractEntity {

    public static final String TABLE_NAME = "paychecks";

    private Float amount;

    private LocalDate date;

    @ManyToOne
    private User user;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
