package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "token")
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    @SequenceGenerator(name = "token_sequence", allocationSize = 1)
    @Column(name = "record_id")
    private Long id;
    @Column(name = "refreshToken")
    private String refreshToken;
    @OneToOne
    @JsonIgnore
    private User user;
    @Transient
    private String accessToken;
}
