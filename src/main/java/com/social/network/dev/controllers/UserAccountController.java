package com.social.network.dev.controllers;

import com.social.network.dev.dto.UserAccountDTO;
import com.social.network.dev.entities.UserAccount;

import com.social.network.dev.service.UserAccountService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/list")
    public List<UserAccount> getUsers() {
        return this.userAccountService.getUsers();
    }

    @PostMapping("/create")
    public ResponseEntity<UserAccountDTO> saveUserAccount(@Valid @RequestBody UserAccountDTO userAccountDTO) {
        return new ResponseEntity<>(userAccountService.saveUser(userAccountDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Optional<UserAccount> getUserById(@PathVariable Long id) {
        return this.userAccountService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserAccount> updateUserById(@Valid @RequestBody UserAccount request, @PathVariable("id") Long id) {
        try {
            UserAccount userResponse = userAccountService.updateUser(request, id);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userAccountService.updateUser(request, id));
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        try {
            userAccountService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }
}
