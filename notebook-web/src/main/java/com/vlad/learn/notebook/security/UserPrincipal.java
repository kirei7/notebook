package com.vlad.learn.notebook.security;

import com.vlad.learn.notebook.core.entity.UserAccount;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;

@EqualsAndHashCode(of = "userAccount")
public class UserPrincipal extends User {

    @Getter
    private UserAccount userAccount;

    public UserPrincipal(UserAccount userAccount) {
        super(
                userAccount.getEmail(),
                userAccount.getPasswordHash(),
                //AuthorityUtils.createAuthorityList(user.getRoles().stream().map(Enum::toString).toArray(String[]::new))
                Collections.emptyList()
                );
        this.userAccount = userAccount;
    }

}
