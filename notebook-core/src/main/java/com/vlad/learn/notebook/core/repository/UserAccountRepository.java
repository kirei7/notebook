package com.vlad.learn.notebook.core.repository;

import com.vlad.learn.notebook.core.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
