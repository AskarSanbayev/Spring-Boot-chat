package com.chat.cyber.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "refs")
public class Refs {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_refs")
    @SequenceGenerator(name = "sq_refs", allocationSize = 1)
    @Column(name = "record_id")
    private Long id;

    @Column(name = "codeName", nullable = false)
    @Size(max = 512, message = "Не больше 512 знаков")
    private String codeName;

    @Column(name = "name", nullable = false)
    @Size(max = 512, message = "Не больше 512 знаков")
    private String name;
}
