package com.everis.paycheckType.boundary;

import com.everis.paycheckType.entity.PaycheckType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PaycheckTypeService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<PaycheckType> getAllPaycheckTypes() {
        Query query = this.entityManager.createNamedQuery(PaycheckType.GET_ALL, PaycheckType.class);
        return query.getResultList();
    }

    public PaycheckType createPaycheckType(PaycheckType paycheckType) {
        this.entityManager.persist(paycheckType);
        this.entityManager.flush();
        return paycheckType;
    }

}
