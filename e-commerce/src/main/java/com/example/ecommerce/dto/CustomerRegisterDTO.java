package com.example.ecommerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerRegisterDTO {

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "email is required and should be unique")
    @Email(message = "enter a valid email")
    private String email;

    @NotNull(message = "password is mandatory ")
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,64})",message="Password must be 8 characters long and should contain uppercase,lowercase,digit,and special character")
    private String password;

    @NotNull(message = "password is mandatory ")
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,64})",message="Password must be 8 characters long and should contain uppercase,lowercase,digit,and special character")
    private String confirmPassword;

    @NotNull(message = "first name is required")
    private String firstName;

    private String middleName;

    @NotNull(message = "last name is required")
    private String lastName;

    @Pattern(regexp = "((\\+*)((0[ -]+)*|(91 )*)(\\d{12}+|\\d{10}+))|\\d{5}([- ]*)\\d{6}",message = "enter a valid phone number")
    @NotNull(message = "contact number required")
    private String contactNumber;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
