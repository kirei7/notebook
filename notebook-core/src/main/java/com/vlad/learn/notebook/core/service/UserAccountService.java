package com.vlad.learn.notebook.core.service;

import com.vlad.learn.notebook.core.entity.UserAccount;
import com.vlad.learn.notebook.core.dto.UserForm;

public interface UserAccountService {
    UserAccount registerUser(UserForm userData);
}
