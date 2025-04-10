package com.mybookstore.authservice.model;

// Removed unused import lombok.AllArgsConstructor

// Ensure this class exists in the specified package// Ensure this is the User class
public class User {
    private Long id;
    private String username;
    private String password;

    // Existing fields and methods

    // Add the missing constructor
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Add getters and setters if not already present
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}