package com.everis.home.boundary;

import com.everis.home.entity.Home;
import com.everis.home.exceptions.HomeNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class HomeService {

    @PersistenceContext
    private EntityManager entityManager;

    public Home createHome(Home home) {
        this.entityManager.persist(home);
        this.entityManager.flush();
        return home;
    }

    public List<Home> getAllHomes() {
        Query query = this.entityManager.createNamedQuery(Home.GET_ALL, Home.class);
        return query.getResultList();
    }

    public Home getHomeById(long id) {
        Home home = this.entityManager.find(Home.class, id);
        if (home == null)
            throw new HomeNotFoundException();
        return home;
    }

    public Home updateHome(Home home) {
        Home homeUpdate = this.getHomeById(home.getId());
        homeUpdate.update(home);
        return homeUpdate;
    }

    public Home removeHome(long id) {
        Home homeRemoved = this.getHomeById(id);
        this.entityManager.remove(homeRemoved);
        return homeRemoved;
    }
}
