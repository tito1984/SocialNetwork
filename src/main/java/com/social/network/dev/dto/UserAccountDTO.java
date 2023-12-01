package com.social.network.dev.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDTO {

    private Long id;
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, message = "User must have at least 3 characters")
    @Size(max = 70, message = "User cant have more than 70 characters")
    private String username;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    //    Todo: Create the password validation system  and save the password in the db with a hash
    @NotEmpty(message = "Password cant be empty")
    @Size(min = 8, message = "Password must have at least 3")
    private String password;
    private Boolean is_active;
    private LocalDate created_at;


    @Builder
    public UserAccountDTO(@NotEmpty(message = "Username cannot be empty")
                          @Size(min = 3, message = "User must have at least 3 characters")
                          @Size(max = 70, message = "User cant have more than 70 characters") String username,
                          @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
                          @NotEmpty(message = "Email cannot be empty")
                          String email) {
        this.username = username;
        this.email = email;

    }
}
