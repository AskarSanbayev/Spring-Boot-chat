package com.chat.cyber.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_like_table")
public class UserLike {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user")
    @SequenceGenerator(name = "sq_user", allocationSize = 1)
    @Column(name = "record_id")
    private Long id;

    @Column(name = "likesCount")
    private Long likesCount;

    @OneToOne(mappedBy = "userLike", cascade = CascadeType.ALL)
    private Post post;

    @OneToOne(mappedBy = "userLike", cascade = CascadeType.ALL)
    private Comment comment;

    @OneToMany
    private List<User> author = new ArrayList<>();
}
