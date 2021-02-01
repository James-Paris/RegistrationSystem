package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userlogin")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private int id;

    @Column(name = "username")
    private String user;

    @Column(name = "password")
    private String pass;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
    	return user;
    }
    
    public void setUser(String user) {
    	this.user = user;
    }
  
    public String getPass() {
    	return pass;
    }
    
    public void setPass(String pass) {
    	this.pass = pass;
    }
 }
