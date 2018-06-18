package com.vlad.learn.notebook.core.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(of = "email")
@EqualsAndHashCode(of = "email")
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NonNull
    @Column(columnDefinition = "VARCHAR(255)\n" +
            "      CHARACTER SET ascii\n" +
            "      NOT NULL UNIQUE")
    private String email;

    @NonNull
    @Column(nullable = false)
    private String passwordHash;

    @OneToOne(mappedBy = "userAccount", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private UserProfile userProfile;

}
