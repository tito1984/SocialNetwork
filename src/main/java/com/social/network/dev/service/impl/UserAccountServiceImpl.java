package com.social.network.dev.service.impl;

import com.social.network.dev.dto.UserAccountDTO;
import com.social.network.dev.dto.UserResponse;
import com.social.network.dev.entities.UserAccount;
import com.social.network.dev.exceptions.ResourceNotFoundException;
import com.social.network.dev.repository.UserAccountRepository;
import com.social.network.dev.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;


    public UserResponse getUsers(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<UserAccount> users = userAccountRepository.findByActiveTrue(pageable);

        List<UserAccount> userList = users.getContent();

        List<UserAccountDTO> content = userList.stream().map(user -> user.mapDTO())
                .toList();

        UserResponse userResponse = new UserResponse();
        userResponse.setContent(content);
        userResponse.setPageNumber(users.getNumber());
        userResponse.setPageSize(users.getSize());
        userResponse.setTotalElements(users.getTotalElements());
        userResponse.setTotalPages(users.getTotalPages());
        userResponse.setLastPage(users.isLast());

        return userResponse;

    }


    public UserAccountDTO saveUser(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = new UserAccount();

        userAccount.mapEntity(userAccountDTO);

        UserAccount newUserAccount = userAccountRepository.save(userAccount);
        return newUserAccount.mapDTO();
    }

    public UserAccount getUserById(Long id) {
        return userAccountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "UserAccount", "id", id));

    }

    public UserAccount updateUser(UserAccount request, Long id) {
        UserAccount user = userAccountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "UserAccount", "id", id));
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getPassword() != null) {
            user.setPassword(request.getPassword());
        }

        user.setModified_at(new Date());

        return userAccountRepository.save(user);


    }

    public void deleteUser(Long id) {
        UserAccount user = userAccountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "UserAccount", "id", id));
        user.setActive(false);
        user.setDeleted_at(new Date());
        userAccountRepository.save(user);
    }


//    Todo: create the auth system
}
