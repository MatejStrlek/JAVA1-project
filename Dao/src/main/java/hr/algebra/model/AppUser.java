/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import hr.algebra.enums.User;

/**
 *
 * @author matej.galic
 */
public class AppUser {
    
    private int id;
    private String username;
    private String password;
    private User appUserRole;

    public AppUser(String username, String password, User appUserRole) {
        this.username = username;
        this.password = password;
        this.appUserRole = appUserRole;
    }
    
    public AppUser(int id, String username, String password, User appUserRole){
        this(username, password, appUserRole);
        this.id = id;
    }

    public AppUser(int id, User appUserRole) {
        this.id = id;
        this.appUserRole = appUserRole;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User getAppUserRole() {
        return appUserRole;
    }

    @Override
    public String toString() {
        return id + " - " + username + " - " + appUserRole;
    }
      
}
