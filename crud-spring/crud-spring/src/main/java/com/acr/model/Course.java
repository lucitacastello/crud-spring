package com.acr.model;

import com.acr.enuns.Category;
import com.acr.enuns.Status;
import com.acr.enuns.converters.CategoryConverter;
import com.acr.enuns.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
//cria a tabela no DB com o mesmo nome da classe
//não precisa usar @Table

@SQLDelete( sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?") //para não mexer nos códigos
@Where(clause = "status = 'Ativo' ") //filtrando com a clausula WHERE
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @JsonProperty("_id") //propriedade do Jackson
    private Long id;

    //usando validation
    @NotBlank
    @NotNull
    @Length(min = 4, max = 100)
    @Column(length = 100, nullable = false)
    private String name;


    //@Length(max = 10)
  //  @Pattern(regexp = "Back-end|Front-end") //permite somente esses dois valores
   // @Enumerated(EnumType.STRING) // EnumType.ORDINAL = número
    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter =  CategoryConverter.class) //quem é o conversor para o ENUM
    private Category category;


   // @Length(max = 10)
   // @Pattern(regexp = "Ativo|Inativo") //permite somente esses dois valores
   @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    //@JoinColumn(name = "course_id" ) //não é a melhor forma, problema de performance em produção, problema de n + 1
    //esse relacionamento é mapeado pelo course e tb pela lesson
    //ver na classe Lesson
    private List<Lesson> lessons = new ArrayList<>();
    
}
