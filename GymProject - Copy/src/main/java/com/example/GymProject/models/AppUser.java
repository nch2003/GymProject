/** Clasa pentru AppUser
 * @author Dan Nicholas-Bogdan
 * @version 10 Decembrie 2024
 */
package com.example.GymProject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(nullable = true)
    private String tipAb;

    @Column(nullable = false)
    private int admin = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getTipAb() {
        return tipAb;
    }

    public void setTipAb(String tipAb) {
        this.tipAb = tipAb;
    }

    public boolean isAdmin() {
        return admin==1;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
