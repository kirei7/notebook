package com.vlad.learn.notebook.core.validation;

import com.vlad.learn.notebook.core.dto.UserForm;
import com.vlad.learn.notebook.core.exception.validation.InvalidEmailException;
import com.vlad.learn.notebook.core.exception.validation.InvalidPasswordException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class UserFormValidatorTest {

    private UserFormValidator userFormValidator;
    private UserForm userForm;
    private final int passwordLengthMin = 4;
    private final int passwordLengthMax = 16;
    private final String acceptableEmail = "somemail@mail.com";
    private final String acceptablePassword = "somemail@mail.com";

    @Before
    public void init() {
        userFormValidator = new UserFormValidator(passwordLengthMin, passwordLengthMax);
    }

    @Test(expected = InvalidEmailException.class)
    @Parameters(method = "invalidEmails")
    public void invalidEmail(String invalidEmail) {
        userForm = new UserForm(invalidEmail, acceptablePassword);
        userFormValidator.validate(userForm);
    }
    public static Object[] invalidEmails() {
        return new Object[]{
                "google.com",
                "@google.com",
                "google.com@",
                "google@com",
                "google.com@com"
        };
    }

    @Test(expected = InvalidPasswordException.class)
    public void passwordTooShort() {
        String tooShortPassword = "Aa2";
        userForm = new UserForm(acceptableEmail, tooShortPassword);
        assertEquals(passwordLengthMin - 1, tooShortPassword.length());
        userFormValidator.validate(userForm);
    }

    @Test(expected = InvalidPasswordException.class)
    public void passwordTooLong() {
        String tooLongPassword = "Aaaaaaaaaaaaaaaa2";
        userForm = new UserForm(acceptableEmail, tooLongPassword);
        assertEquals(passwordLengthMax + 1, tooLongPassword.length());
        userFormValidator.validate(userForm);
    }

    @Test(expected = InvalidPasswordException.class)
    @Parameters(method = "passwordsWithWhitespaces")
    public void passwordContainsWhitespaces(Supplier<String> passwordWithWhitespace) {
        userForm = new UserForm(acceptableEmail, passwordWithWhitespace.get());
        userFormValidator.validate(userForm);
    }
    //Strings are wrapped in Suppliers, because something
    //is trimming them when they are passed to test method
    private Object[] passwordsWithWhitespaces() {
        return new Supplier[]{
                () -> "pass word",
                () -> "pass\tword",
                () -> " password ",
                () -> "\tpassword\t",
                () -> "\tpassword",
                () -> "password\t"
        };
    }


}
