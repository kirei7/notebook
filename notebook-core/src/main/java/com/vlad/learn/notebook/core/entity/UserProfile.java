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
@ToString
@EqualsAndHashCode
public class UserProfile implements Serializable {

    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserAccount userAccount;
    private String name = "Default name";
    @OneToMany(mappedBy = "userProfile")
    private List<Note> notes = new ArrayList<>();

    public UserProfile(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

}
