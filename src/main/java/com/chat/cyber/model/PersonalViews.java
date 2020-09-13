package com.chat.cyber.model;

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

    @OneToOne
    @JoinColumn(name = "user_fk", referencedColumnName = "record_id", nullable = false)
    private User user;

    @ManyToOne
    @Column(name = "political_view_fk")
    private RefsValues politicalViews;

    @ManyToOne
    @Column(name = "personal_priority_fk")
    private RefsValues personalPriority;

    @ManyToOne
    @Column(name = "religion_fk")
    private RefsValues religion;

    @ManyToOne
    @Column(name = "important_in_others_fk")
    private RefsValues importantInOthers;

    @ManyToOne
    @Column(name = "smoking_view_fk")
    private RefsValues viewsOnSmoking;

    @ManyToOne
    @Column(name = "alcohol_view_fk")
    private RefsValues viewsOnAlcohol;

    private String inspiredBy;
}
