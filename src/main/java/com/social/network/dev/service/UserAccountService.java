package com.social.network.dev.service;

import com.social.network.dev.dto.UserAccountDTO;
import com.social.network.dev.entities.UserAccount;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserAccountService {
    public List<UserAccount> getUsers();

    public UserAccountDTO saveUser(UserAccountDTO userAccountDTO);

    public Optional<UserAccount> getUserById(Long id);

    public UserAccount updateUser(UserAccount request, Long id);

    public void deleteUser(Long id);

}
