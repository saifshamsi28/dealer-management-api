package com.saif.dealer.dto;

public class AuthResponse {
    private String token;
    private String username;
    private Long id;
    private Long expirationTime;

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse(String token, String username, Long id) {
        this.token = token;
        this.username = username;
        this.id = id;
    }

    public AuthResponse(String token, String username, Long id, Long expirationTime) {
        this.token = token;
        this.username = username;
        this.id = id;
        this.expirationTime = expirationTime;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getExpirationTime() { return expirationTime; }
    public void setExpirationTime(Long expirationTime) { this.expirationTime = expirationTime; }
}
