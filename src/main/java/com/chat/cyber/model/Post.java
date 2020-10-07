package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_post")
    @SequenceGenerator(name = "sq_post", allocationSize = 1)
    @JsonIgnore
    @Column(name = "record_id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "creation_date")
    private Date creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Column(name = "post_text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Comment.class)
    private List<Comment> postComments;
}

