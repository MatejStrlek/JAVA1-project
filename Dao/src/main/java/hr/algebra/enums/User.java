/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.enums;

/**
 *
 * @author matej.galic
 */
public enum User {
    DEFAULT,
    ADMIN;
    
    public static User fromString(String value) {
        if (value != null) {
            switch (value.toUpperCase()) {
                case "DEFAULT":
                    return DEFAULT;
                case "ADMIN":
                    return ADMIN;
            }
        }
        throw new IllegalArgumentException("Invalid User: " + value);
    }

}
