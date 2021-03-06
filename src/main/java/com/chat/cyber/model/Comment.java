package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_comment")
    @SequenceGenerator(name = "sq_comment", allocationSize = 1)
    @Column(name = "record_id")
    private Long id;

    @Column(name = "text")
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "creation_date")
    private Date creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "modified_date")
    private Date lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "post_fk", nullable = false)
    private Post post;

    @Column(name = "author_fk", nullable = false)
    private Long authorId;

    @ManyToMany
    @JoinTable(
            name = "comment_likes",
            joinColumns = {@JoinColumn(name = "comment_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> likes = new HashSet<>();
}
