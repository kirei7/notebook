package com.vlad.learn.notebook.core.config;

import com.vlad.learn.notebook.core.dto.UserForm;
import com.vlad.learn.notebook.core.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

/*Contains some features for development phase,
* like default users etc.*/
@Profile("dev")
@Configuration
public class DevConfig {

    private final String userDefaultEmail;
    private final String userDefaultPassword;


    @Autowired
    private UserAccountService userAccountService;

    public DevConfig(
            @Value("${default.user.email}")String userDefaultEmail,
            @Value("${default.user.password}")String userDefaultPassword
    ) {
        this.userDefaultEmail = userDefaultEmail;
        this.userDefaultPassword = userDefaultPassword;
    }

    @PostConstruct
    public void registerDefaultUser() {
        userAccountService.registerUser(new UserForm(userDefaultEmail, userDefaultPassword));
    }
}
