package com.acr.service;

import com.acr.dto.CourseDTO;
import com.acr.dto.mapper.CourseMapper;
import com.acr.exception.RecordNotFoundException;
import com.acr.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    // @Autowired
    // coloca a propriedade no construtor para injeção de dependência
    //    aqui não vale a pena usar Lombok
    private final CourseRepository courseRepository;
    public final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> findAll() {
        //programação reativa
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());

       /* List<Course> courses = courseRepository.findAll();
        List<CourseDTO> dtos = new ArrayList<>(courses.size());
        for(Course course : courses ){
            CourseDTO dto = new CourseDTO(course.getId(), course.getName(),course.getCategory());
            dtos.add(dto);
        }
        return dtos;*/
    }

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow( () -> new RecordNotFoundException(id)) ;

    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {

        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(this.courseMapper.convertCategoryValue(course.category()));
                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow( () -> new RecordNotFoundException(id)) ;

    }

    public void delete(@NotNull @Positive Long id) {

        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow( () -> new RecordNotFoundException(id))) ;

        //veririca se existe na base
        /* courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return true;
                })
                .orElseThrow( () -> new RecordNotFoundException(id)) ;*/

    }

}
