package com.kayak.batchManager.ManagerControl.Client.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClientDTO {

    private Long id;

    @NotBlank(message = "Name is necessary")
    private String name;

    @NotBlank(message = "Phone is necessary")
    private String phone;

    @NotBlank(message = "Email is necessary")
    @Email(message = "Email need to be valid")
    private String email;

    // Getters and Setters
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
}
