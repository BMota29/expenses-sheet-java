package com.everis.home.entity;

import com.everis.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = Home.TABLE_NAME)
@NamedQueries({
        @NamedQuery( name = Home.GET_ALL, query = "SELECT h FROM Home h")
})
public class Home extends AbstractEntity {

    public static final String TABLE_NAME = "homes";
    public static final String GET_ALL = "com.everis.home.getAll";

    private String description;

    private String addressName;

    private String addressNumber;

    private String addressFlor;

    public Home() {
        super();
    }

    //Startup only
    public Home(String description, String addressName, String addressNumber, String addressFlor) {
        this.description = description;
        this.addressName = addressName;
        this.addressNumber = addressNumber;
        this.addressFlor = addressFlor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressFlor() {
        return addressFlor;
    }

    public void setAddressFlor(String addressFlor) {
        this.addressFlor = addressFlor;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public void update(Home home) {
        this.description = home.getDescription();
        this.addressName = home.getAddressName();
        this.addressNumber = home.getAddressNumber();
        this.addressFlor = home.getAddressFlor();
    }

}
