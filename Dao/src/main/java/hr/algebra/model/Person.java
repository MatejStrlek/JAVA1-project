/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import hr.algebra.enums.Role;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matej.galic
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Comparable<Person>{

    @XmlAttribute
    private int id;
    
    @XmlElement(name = "name")
    private String name;
    
    @XmlElement(name = "role")
    private Role role;
    
    @XmlTransient
    private int movieId;

    public Person() {
    } 

    public Person(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public Person(int id, String name, Role role, int movieId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.movieId = movieId;
    }

    public Person(String name, Role role, int movieId) {
        this.name = name;
        this.role = role;
        this.movieId = movieId;
    }

    public Person(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
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

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + role;
    }

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.name);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        return Objects.equals(this.name, other.name);
    }

           
}
