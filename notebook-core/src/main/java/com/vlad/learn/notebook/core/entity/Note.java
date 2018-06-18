package com.vlad.learn.notebook.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "title"})
@EqualsAndHashCode(of = "id")
public class Note implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;
}
