package com.acr.dto.mapper;

import com.acr.dto.CourseDTO;
import com.acr.dto.LessonDTO;
import com.acr.enuns.Category;
import com.acr.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl() ))
                .collect(Collectors.toList());

        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessons);
    }

    public Course toEntity(CourseDTO courseDTO) {

        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        //course.setStatus("Ativo");
        return course;
    }

    //para pegar qual é o enumerator
    public Category convertCategoryValue(String value) {

        if (value == null) {
            return null;
        }
        //expressão switch diferente do switch case
       return switch (value) {
            case "Front-end" -> Category.FRONT_END; //lambda
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value);
        };

    }

}
