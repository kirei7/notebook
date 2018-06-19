package com.vlad.learn.notebook.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"userAccount", "name"})
@EqualsAndHashCode(of = "userAccount")
public class UserProfile implements Serializable {

    @Id
    private Long id;
    @OneToOne
    @MapsId
    private UserAccount userAccount;
    private String name;
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.PERSIST)
    private List<Note> notes = new ArrayList<>();

    public UserProfile(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

}
