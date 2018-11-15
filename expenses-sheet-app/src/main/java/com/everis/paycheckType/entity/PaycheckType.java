package com.everis.paycheckType.entity;

import com.everis.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = PaycheckType.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = PaycheckType.GET_ALL, query = "SELECT p FROM PaycheckType p")
})
public class PaycheckType extends AbstractEntity {

    public static final String TABLE_NAME = "paycheckTypes";
    public static final String GET_ALL = "com.everis.paycheckType.getAll";

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
