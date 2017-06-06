package ua.training.entity;

import java.io.Serializable;

/**
 * Created by vitaliy on 21.05.17.
 */
public class User implements Serializable{
    private Integer id;
    private String login;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Wallet wallet;
    private UserRole userRole;

    public User(Integer id, String login, String name, String surname, String email) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean withdraw(Double price){
        if(wallet.getCount()>=price){
            wallet.setCount(wallet.getCount()-price);
            return true;
        }
        return false;
    }
}
