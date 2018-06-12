package com.vlad.learn.notebook.core.service;

import com.vlad.learn.notebook.core.entity.UserAccount;
import com.vlad.learn.notebook.core.dto.UserForm;
import com.vlad.learn.notebook.core.exception.UserAccountNotFoundException;
import com.vlad.learn.notebook.core.repository.UserAccountRepository;
import com.vlad.learn.notebook.core.validation.UserFormValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountRepository userAccountRepository;
    private PasswordEncoder passwordEncoder;
    private UserFormValidator userFormValidator;

    public UserAccountServiceImpl(
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder,
            UserFormValidator userFormValidator) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.userFormValidator = userFormValidator;
    }

    @Override
    public UserAccount registerUser(UserForm userData) {
        userFormValidator.validate(userData);
        UserAccount newUserAccount = new UserAccount(
                userData.getEmail(),
                passwordEncoder.encode(userData.getPassword())
        );
        return userAccountRepository.save(newUserAccount);
    }

    @Override
    public UserAccount findByEmail(String email) {
        return userAccountRepository
                .findById(email)
                .orElseThrow(() -> new UserAccountNotFoundException("There is not user with such email"));
    }

}
