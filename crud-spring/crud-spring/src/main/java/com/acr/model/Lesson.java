package com.acr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false) //não pode ser nulo
    private String name;

    @Column(length = 11, nullable = false)
    private String youtubeUrl;

    //mapeando o OneToMany de Course

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //carrega o mapeamento somente qdo chamar getCourse
    @JoinColumn(name = "course_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //para evitar chamadas circular entre as classes, só faz o set
    private Course course;



}
