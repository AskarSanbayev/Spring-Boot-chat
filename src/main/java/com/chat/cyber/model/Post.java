package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post implements Serializable {

    private Long id;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
    private String text;
    private User author;
    private Set<Comment> postComments;

    public Post() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    @SequenceGenerator(name = "post_sequence", allocationSize = 1)
    @Column(name = "post_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "creation_date")
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_modified_date")
    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Column(name = "post_text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST},targetEntity = Comment.class)
    public Set<Comment> getPostComments() {
        return postComments;
    }

    public void setPostComments(Set<Comment> postComments) {
        this.postComments = postComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(creationDate, post.creationDate) &&
                Objects.equals(lastModifiedDate, post.lastModifiedDate) &&
                Objects.equals(text, post.text) &&
                Objects.equals(author, post.author) &&
                Objects.equals(postComments, post.postComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, lastModifiedDate, text, author, postComments);
    }
}

