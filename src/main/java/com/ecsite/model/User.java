package com.ecsite.model;

public class User {
    private Long id;         // ユーザーID
    private String email;    // Email
    private String username; // ユーザー名
    private String password; // パスワード

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // ゲッターとセッター
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
