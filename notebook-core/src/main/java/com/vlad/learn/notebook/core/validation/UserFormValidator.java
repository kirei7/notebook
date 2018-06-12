package com.vlad.learn.notebook.core.validation;

import com.vlad.learn.notebook.core.dto.UserForm;
import com.vlad.learn.notebook.core.exception.validation.InvalidEmailException;
import com.vlad.learn.notebook.core.exception.validation.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserFormValidator {

    private final int minPasswordLength;
    private final int maxPasswordLength;

    private Pattern containsWhitespacesPattern = Pattern.compile("\\s");
    private Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");

    public UserFormValidator(
            @Value("${validation.password.length.min}") int minimumPasswordLength,
            @Value("${validation.password.length.max}") int maximumPasswordLength) {
        this.minPasswordLength = minimumPasswordLength;
        this.maxPasswordLength = maximumPasswordLength;
    }

    public void validate(UserForm userForm) {
        validateEmail(userForm.getEmail());
        validatePassword(userForm.getPassword());
    }

    private void validateEmail(String email) {
        if (containsWhitespaces(email)) {
            throw new InvalidEmailException("Email shouldn't contain whitespaces");
        }
        Matcher mat = emailPattern.matcher(email);
        if(!mat.matches()){
            throw new InvalidEmailException();
        }
    }

    private void validatePassword(String password) {
        if (containsWhitespaces(password)) {
            throw new InvalidPasswordException("Password shouldn't contain whitespaces");
        }
        if (password.length() < minPasswordLength) {
            throw new InvalidPasswordException("Password length can't be less than " + minPasswordLength + " symbols");
        }
        if (password.length() > maxPasswordLength) {
            throw new InvalidPasswordException("Password length can't be more than " + maxPasswordLength + " symbols");
        }

    }

    private boolean containsWhitespaces(String field) {
        //normally, fields are trimmed so they shouldn't
        //contain any whitespaces, but just in case...
        Pattern pattern = containsWhitespacesPattern;
        Matcher matcher = pattern.matcher(field);
        return matcher.find();
    }
}
