package edu.eci.dosw.oficioya.model;

import java.time.LocalDateTime;

public class UserModel {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private LocalDateTime registerDate;
    private boolean activeAccount;

    public UserModel() {
    }

    public UserModel(String name, String phone, String email, String password, LocalDateTime registerDate, boolean activeAccount) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.registerDate = registerDate;
        this.activeAccount = activeAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(boolean activeAccount) {
        this.activeAccount = activeAccount;
    }
}
