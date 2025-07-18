/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia.model;

import java.time.LocalDateTime;

/**
 *
 * @author denia
 */
public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String role;
    private String imagePath;
    private LocalDateTime timestamp;
    
    public User() {
        this.userId = 0;
        this.username = null;
        this.email = null;
        this.password = null;
        this.role = null;
        this.imagePath = null;
    }
    
    public User(int userId, String username, String email, String password, String role, LocalDateTime timestamp){
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.timestamp = timestamp;
    }
    
    public User(int userId, String username, String email, String password, String role, String imagePath, LocalDateTime timestamp) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.imagePath = imagePath;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password + ", role=" + role + ", imagePath=" + imagePath + '}';
    }
    
    
}
