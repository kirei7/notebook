package com.vlad.learn.notebook.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserForm {
    private String email;
    private String password;
}
