package com.vlad.learn.notebook.core.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "email")
@EqualsAndHashCode(of = "email")
public class UserAccount implements Serializable {
    @Id
    @Email
    private String email;
    private String passwordHash;
}
