/** Clasa pentru RegisterDto
 * @author Dan Nicholas-Bogdan
 * @version 10 Decembrie 2024
 */
package com.example.GymProject.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class RegisterDto {
    @NotEmpty
    private String username;

    @NotEmpty
    @Email
    private String email;


    private String password;

    private String confirmPassword;

    public @NotEmpty String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty String username) {
        this.username = username;
    }

    public @NotEmpty @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @Email String email) {
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
}
