package com.myApp.POJO.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Fedir on 09.10.2015.
 */
@Entity
@Table(name = "user", uniqueConstraints=
@UniqueConstraint(columnNames={"username"}))
public class User implements Serializable{

    @Id
    @Column(name = "username", length = 1024, nullable = false)
    private String username;

    @Column(name = "firstname", length = 1024, nullable = false)
    private String firstname;

    @Column(name = "password", length = 32, nullable = false)
    private String password;

    @Column(name = "salt", length = 32, nullable = false)
    private String salt;


    public String getFirstname() {
        return firstname;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public String getSalt() {
        return salt;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }




}
