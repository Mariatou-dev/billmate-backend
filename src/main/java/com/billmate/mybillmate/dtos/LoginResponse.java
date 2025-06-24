package com.billmate.mybillmate.dtos;

public class LoginResponse {
    private String message;
    private String username;
    private Integer id;

    public LoginResponse() {
    }

    public LoginResponse(String message, String username, Integer id) {
        this.message = message;
        this.username = username;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
