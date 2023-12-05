package com.social.network.dev.repository;

import com.social.network.dev.entities.UserAccount;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Page<UserAccount> findByActiveTrue(Pageable pageable);
}

