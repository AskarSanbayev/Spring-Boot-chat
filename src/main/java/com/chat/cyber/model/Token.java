package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "token")
public class Token implements Serializable {

    private Long id;
    private String refreshToken;
    private User user;
    private String accessToken;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    @SequenceGenerator(name = "token_sequence", allocationSize = 1)
    @Column(name = "token_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "refreshToken")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @OneToOne
    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(id, token.id) &&
                Objects.equals(refreshToken, token.refreshToken) &&
                Objects.equals(user, token.user) &&
                Objects.equals(accessToken, token.accessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, refreshToken, user, accessToken);
    }
}
