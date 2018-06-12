package com.vlad.learn.notebook.core.service;

import com.vlad.learn.notebook.core.entity.UserAccount;
import com.vlad.learn.notebook.core.dto.UserForm;
import com.vlad.learn.notebook.core.repository.UserAccountRepository;
import com.vlad.learn.notebook.core.validation.UserFormValidator;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserAccountServiceImplTest {

    private UserAccountServiceImpl userAccountService;
    private UserAccountRepository userAccountRepository;
    private PasswordEncoder passwordEncoder;
    private UserFormValidator userFormValidator;

    private static final String SAMPLE_EMAIL = "user@email.com";
    private static final String SAMPLE_PASSWORD = "s0m3pa$$word";

    private final UserForm testForm;

    public UserAccountServiceImplTest() {
        userAccountRepository = userAccountRepositoryMock();
        passwordEncoder = passwordEncoderMock();
        userFormValidator = userFormValidatorMock();
        userAccountService = new UserAccountServiceImpl(userAccountRepository, passwordEncoder, userFormValidator);

        testForm = new UserForm(SAMPLE_EMAIL, SAMPLE_PASSWORD);
    }
    private UserAccountRepository userAccountRepositoryMock() {
        UserAccountRepository userAccountRepository = Mockito.mock(UserAccountRepository.class);
        //make 'save' method return passed argument
        when(userAccountRepository.save(any(UserAccount.class))).thenAnswer((Answer<UserAccount>) invocation -> {
            Object[] args = invocation.getArguments();
            return (UserAccount) args[0];
        });
        return userAccountRepository;
    }
    private PasswordEncoder passwordEncoderMock() {
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        when(passwordEncoder.encode(SAMPLE_PASSWORD)).thenReturn("It's sample password's hash!");
        return passwordEncoder;
    }
    private UserFormValidator userFormValidatorMock() {
        return Mockito.mock(UserFormValidator.class);
    }

    @Test
    public void userFormShouldBeValidated() {
        userAccountService.registerUser(testForm);
        verify(userFormValidator, times(1)).validate(testForm);
    }

    @Test
    public void passwordShouldBeEncoded() {
        userAccountService.registerUser(testForm);
        verify(passwordEncoder, times(1)).encode(SAMPLE_PASSWORD);
    }

    @Test
    public void newUserAccountMustBeSaved() {
        userAccountService.registerUser(testForm);
        verify(userAccountRepository, times(1)).save(any(UserAccount.class));
    }

    @Test
    public void registerMethodShouldReturnNewlyRegisteredUser() {
        UserAccount userAccount = userAccountService.registerUser(testForm);
        assertEquals(SAMPLE_EMAIL, userAccount.getEmail());
        assertEquals(passwordEncoder.encode(SAMPLE_PASSWORD), userAccount.getPasswordHash());
    }

}
