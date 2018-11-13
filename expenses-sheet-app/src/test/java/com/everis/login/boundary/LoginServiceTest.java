package com.everis.login.boundary;

import com.everis.login.entity.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class LoginServiceTest {

    private static EntityManagerFactory emf;

    @BeforeClass
    public static void init(){
        emf = Persistence.createEntityManagerFactory("tests");
    }

    private LoginService underTest;

    @Before
    public void setUp() {
        underTest = new LoginService();
        underTest.setEntityManager(emf.createEntityManager());
    }

    @Test
    public void createUser() throws Exception {
        EntityTransaction tx = underTest.getEntityManager().getTransaction();
        //Given
        User user = new User();
        user.setEmail("admin@test.com");
        user.setPassword("admin");
        user.setUsername("admin");
        //When
        tx.begin();
        User createdUser = underTest.createUser(user);
        //Then
        assertNotNull(createdUser);
        assertNotEquals(0, createdUser.getId() );
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getPassword(), createdUser.getPassword());
        assertEquals(user.getUsername(), createdUser.getUsername());
        tx.rollback();
    }

}
