package com.everis.login.boundary;

import com.everis.login.entity.User;
import com.everis.login.exceptions.AuthUserNotFound;
import com.everis.security.boundary.PasswordEncoded;
import com.everis.security.control.BasicAuthenticationFilter;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class LoginService {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(BasicAuthenticationFilter.class.getName());

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    @PasswordEncoded
    public User createUser(User user) {
        em.persist(user);
        em.flush();
        return user;
    }

    public List<User> getUsers() {
        Query query = this.em.createQuery("select u from User u");
        List<User> list = query.getResultList();
        this.em.detach(list);
        list.forEach( obj -> {
            if (obj instanceof User) {
                ((User) obj).setSalt(null);
            }
        });
        return list;
    }

    @PasswordEncoded
    public User validate(User user) {
        Query query = this.em.createNamedQuery(User.VALIDATE);
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        /*List<User> q = query.getResultList();
        return q.isEmpty() ? null : q.get(0);*/
        User userValidated = null;
        try {
            userValidated = (User)query.getSingleResult();
        } catch (NoResultException e ) {}
        return userValidated;
    }

    public User getUserById(long id) {
        User user = this.em.find(User.class, id);
        if (user == null)
            throw new AuthUserNotFound();
        this.em.detach(user);
        user.setSalt(null);
        return user;
    }

    public User getUserByEmail(String email) {
        Query query = this.em.createNamedQuery(User.GET_BY_EMAIL);
        query.setParameter("email", email);
        List q = query.getResultList();
        if (q.isEmpty()) throw new AuthUserNotFound();
        User user = (User)q.get(0);
        this.em.detach(user);
        user.setSalt(null);
        return user;
    }

    public User getUserByUsername(String username, boolean isSaltNeeded) {
        Query query = this.em.createNamedQuery(User.GET_BY_USERNAME);
        query.setParameter("username", username);
        User user = (User)query.getSingleResult();
        if (user == null)
            throw new AuthUserNotFound();
        if(!isSaltNeeded) {
            this.em.detach(user);
            user.setSalt(null);
        }
        return user;
    }

    public byte[] getUserSalt(String username) {
        try {
            return this.getUserByUsername(username, true).getSalt();
        } catch(NoResultException e) {
            return null;
        }
    }

    public User removeUser(long id) {
        User removedUser = this.getUserById(id);
        this.em.remove(removedUser);
        return removedUser;
    }
}
