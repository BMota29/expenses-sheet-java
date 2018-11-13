package com.everis.login.boundary;

import com.everis.login.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class LoginService {

    @PersistenceContext
    private EntityManager em;

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public User createUser(User user) {
        em.persist(user);
        em.flush();
        return user;
    }

    public List<User> getUsers() {
        Query query = this.em.createQuery("select u from User u");
        return query.getResultList();
    }
}
