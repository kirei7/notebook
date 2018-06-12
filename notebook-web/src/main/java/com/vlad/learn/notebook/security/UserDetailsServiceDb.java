package com.vlad.learn.notebook.security;

import com.vlad.learn.notebook.core.service.UserAccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceDb implements UserDetailsService {

    private UserAccountService userAccountService;

    public UserDetailsServiceDb(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return new UserPrincipal(userAccountService.findByEmail(email));
    }

}
