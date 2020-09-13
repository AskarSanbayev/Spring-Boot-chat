package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", allocationSize = 1)
    @Column(name = "record_id")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "login", nullable = false)
    @NotBlank(message = "Login is a mandatory parameter!")
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is a mandatory parameter!")
    private String password;

    @Column(name = "email")
//    @Email(message = "Email is not correct")
    private String email;

    @Column(name = "age")
    @Min(value = 1, message = "Age must be greater than zero!")
    private int age;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_fk", referencedColumnName = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

    @ManyToMany
    @JoinTable(
            name = "friend_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friendList;

    @OneToMany
    @JoinColumn(name = "user_fk")
    private List<RefsValues> languages = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ContactInfo userContactInfo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Interests userInterests;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CareerInfo> careerInfos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Education> educations = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PersonalViews personalViews;
}
