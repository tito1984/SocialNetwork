package com.social.network.dev.entities;

import com.social.network.dev.dto.UserAccountDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(columnDefinition = "tinyint(1) default 1")
    private Boolean active;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreationTimestamp
    private LocalDate created_at;
    private LocalDate modified_at;
    private LocalDate deleted_at;

    public UserAccountDTO mapDTO() {
        UserAccountDTO UserAccountDTO = new UserAccountDTO();
        UserAccountDTO.setId(this.id);
        UserAccountDTO.setUsername(this.username);
        UserAccountDTO.setEmail(this.email);
        return UserAccountDTO;
    }

    public UserAccount mapEntity(UserAccountDTO userAccountDTO) {
        this.setUsername(userAccountDTO.getUsername());
        this.setEmail(userAccountDTO.getEmail());
        this.setPassword(userAccountDTO.getPassword());
        this.setActive(true);
        return this;
    }


}
