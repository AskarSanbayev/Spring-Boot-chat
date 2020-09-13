package com.chat.cyber.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_education")
public class Education {
    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;

    @Column(name = "type", nullable = false)
    private EducationType educationType;

    @ManyToOne
    @Column(name = "country_fk")
    private RefsValues country;

    @ManyToOne
    @Column(name = "city_fk")
    private RefsValues city;

    @ManyToOne
    @Column(name = "school_fk")
    private RefsValues schoolName;

    private String startDate;

    private String endDate;

    @Size(min = 1, max = 1)
    private String classType;

    private String specialization;

    @ManyToOne
    @Column(name = "faculty_fk")
    private RefsValues faculty;

    @ManyToOne
    @Column(name = "study_type_fk")
    private RefsValues studyType;

    @ManyToOne
    @Column(name = "status_type_fk")
    private RefsValues status;
}
