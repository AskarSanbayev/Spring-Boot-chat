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
    @JoinColumn(name = "country_fk")
    private RefsValues country;

    @ManyToOne
    @JoinColumn(name = "city_fk")
    private RefsValues city;

    @ManyToOne
    @JoinColumn(name = "school_fk")
    private RefsValues schoolName;

    private String startDate;

    private String endDate;

    @Size(min = 1, max = 1)
    private String classType;

    private String specialization;

    @ManyToOne
    @JoinColumn(name = "faculty_fk")
    private RefsValues faculty;

    @ManyToOne
    @JoinColumn(name = "study_type_fk")
    private RefsValues studyType;

    @ManyToOne
    @JoinColumn(name = "status_type_fk")
    private RefsValues status;
}
