package com.chat.cyber.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_contact_info")
public class ContactInfo {
    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk", referencedColumnName = "record_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "country_fk")
    private RefsValues country;

    @ManyToOne
    @JoinColumn(name = "city_fk")
    private RefsValues city;

    @Column(name = "street")
    private String street;

    @Column(name = "flat")
    private String flat;
}
