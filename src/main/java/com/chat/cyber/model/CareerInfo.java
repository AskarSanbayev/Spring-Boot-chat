package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
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
    @JsonIgnore
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
