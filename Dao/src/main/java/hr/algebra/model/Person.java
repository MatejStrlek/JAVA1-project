/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import hr.algebra.enums.Role;

/**
 *
 * @author matej.galic
 */

public class Person {
    
    private int id;
    private String name; 
    private Role role;

    public Person() {
    }

    public Person(String name, Role role) {
        this.name = name;
        this.role = role;
    } 

    public Person(int id, String name, Role role) {
        this(name, role);
        this.id = id;        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + role;
    }

       
    
}
