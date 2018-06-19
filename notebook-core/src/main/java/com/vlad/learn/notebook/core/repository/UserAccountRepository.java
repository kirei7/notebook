package com.vlad.learn.notebook.core.repository;

import com.vlad.learn.notebook.core.entity.UserAccount;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);

    @EntityGraph(attributePaths = { "userProfile", "userProfile.notes"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<UserAccount> getByEmail(String name);
}
