package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
    private LocalDateTime creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "modified_date")
    private LocalDateTime lastModifiedDate;
    @ManyToOne
    @JoinColumn(name = "post_fk", nullable = false)
    private Post post;
    @ManyToOne
    @JoinColumn(name = "author_fk", nullable = false)
    private User author;
}
