package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_personal_views")
public class PersonalViews {

    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk", referencedColumnName = "record_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "political_view_fk")
    private RefsValues politicalViews;

    @ManyToOne
    @JoinColumn(name = "personal_priority_fk")
    private RefsValues personalPriority;

    @ManyToOne
    @JoinColumn(name = "religion_fk")
    private RefsValues religion;

    @ManyToOne
    @JoinColumn(name = "important_in_others_fk")
    private RefsValues importantInOthers;

    @ManyToOne
    @JoinColumn(name = "smoking_view_fk")
    private RefsValues viewsOnSmoking;

    @ManyToOne
    @JoinColumn(name = "alcohol_view_fk")
    private RefsValues viewsOnAlcohol;

    private String inspiredBy;
}
