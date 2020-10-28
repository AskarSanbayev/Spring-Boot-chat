package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user")
    @SequenceGenerator(name = "sq_user", allocationSize = 1)
    @JsonIgnore
    @Column(name = "record_id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "Login is a mandatory parameter!")
    private String username;

    @Column(name = "email")
//    @Email(message = "Email is not correct")
    private String email;

    @Column(name = "age")
    private int age;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

    @ManyToMany
    @JoinTable(
            name = "friend_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<User> friendList;

    @OneToMany
    @JoinColumn(name = "user_fk")
    private List<RefsValues> languages = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private ContactInfo userContactInfo;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Interests userInterests;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CareerInfo> careerInfos = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Education> educations = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private PersonalViews personalViews;

    public void addCareerInfo(CareerInfo careerInfo) {
        careerInfos.add(careerInfo);
        careerInfo.setUser(this);
    }

    public void removeCareerInfo(CareerInfo careerInfo) {
        careerInfos.remove(careerInfo);
        careerInfo.setUser(null);
    }

    public void addEducation(Education education) {
        educations.add(education);
        education.setUser(this);
    }

    public void removeEducation(Education education) {
        educations.remove(education);
        education.setUser(null);
    }
}
