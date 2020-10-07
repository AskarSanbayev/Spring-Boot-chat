package com.chat.cyber.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_career")
public class CareerInfo {
    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;

    private String workPlaceTitle;

    @ManyToOne
    @JoinColumn(name = "country_fk")
    private RefsValues country;

    @ManyToOne
    @JoinColumn(name = "city_fk")
    private RefsValues city;

    private String startDate;

    private String endDate;

    private String roleTitle;
}
