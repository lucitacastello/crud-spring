package com.acr.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class OldCourseDTO {

    private Long id;

    //usando validation
    @NotBlank
    @NotNull
    @Length(min = 4, max = 100)
    private String name;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Back-end|Front-end") //permite somente esses dois valores
    private String category;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo") //permite somente esses dois valores
    private String status = "Ativo";
}
