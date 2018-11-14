package com.everis.login.entity;

import com.everis.common.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.inject.Named;
import javax.persistence.*;

@Entity
@Table(name = User.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name= User.VALIDATE, query = "SELECT u FROM User u " +
                "WHERE u.username = :username AND u.password = :password"),
        @NamedQuery(name = User.GET_BY_USERNAME, query = "SELECT u FROM User u " +
                "WHERE u.username = :username"),
        @NamedQuery(name = User.GET_BY_EMAIL, query = "SELECT u FROM User u " +
                "WHERE u.email = :email")
})
public class User extends AbstractEntity {

    public static final String TABLE_NAME = "users";
    public static final String VALIDATE = "com.everis.user.validate";
    public static final String GET_BY_USERNAME= "com.everis.user.getByUsername";
    public static final String GET_BY_EMAIL = "com.everis.user.getByEmail";

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private byte[] salt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
