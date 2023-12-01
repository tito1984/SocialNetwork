package com.social.network.dev.service.impl;

import com.social.network.dev.dto.UserAccountDTO;
import com.social.network.dev.entities.UserAccount;
import com.social.network.dev.repository.UserAccountRepository;
import com.social.network.dev.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;


    //   Todo: filter this method to return only the users with the value true in the is_active field
    public List<UserAccount> getUsers() {
        return userAccountRepository.findAll();
    }


    public UserAccountDTO saveUser(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = new UserAccount();

        userAccount.mapEntity(userAccountDTO);

        UserAccount newUserAccount = userAccountRepository.save(userAccount);
        return newUserAccount.mapDTO();
    }

    public Optional<UserAccount> getUserById(Long id) {
        return userAccountRepository.findById(id);

    }

    public UserAccount updateUser(UserAccount request, Long id) {
        UserAccount user = userAccountRepository.findById(id).orElseThrow();
//      Todo: fix this to change only one field
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getPassword() != null) {
            user.setPassword(request.getPassword());
        }

        return userAccountRepository.save(user);


    }

    public void deleteUser(Long id) {
        UserAccount user = userAccountRepository.findById(id).orElseThrow();
        user.setIs_active(false);
        userAccountRepository.save(user);
    }


//    Todo: create the auth system
}
