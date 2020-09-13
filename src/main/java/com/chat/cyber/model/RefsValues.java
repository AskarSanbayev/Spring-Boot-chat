package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "refs_values")
public class RefsValues {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_refs_values")
    @SequenceGenerator(name = "sq_refs_values", allocationSize = 1)
    @Column(name = "record_id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ref_rid", nullable = false)
    private Refs refs;

    @Column(name = "name", nullable = false)
    private String name;
}
