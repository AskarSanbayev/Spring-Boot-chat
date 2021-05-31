package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

    @Column(name = "codeName", nullable = false)
    @Size(max = 512, message = "Не больше 512 знаков")
    private String codeName;

    @Column(name = "name", nullable = false)
    private String name;
}
