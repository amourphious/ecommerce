package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


public class PasswordDTO {
    @NotNull(message = "pls enter a new password")
    private String password;

    @NotNull(message = "pls re-enter the new password ")
    private String confirmPassword;

    @NotNull(message = "pls enter token send to mail")
    private String resetToken;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}
