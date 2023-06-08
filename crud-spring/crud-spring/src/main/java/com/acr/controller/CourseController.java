package com.acr.controller;

import com.acr.dto.CourseDTO;
import com.acr.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@AllArgsConstructor
@Validated
@RequestMapping("/api/courses")
public class CourseController {

    //parei em CRUD Angular + Spring | 48: Curso-Aulas: Interface Aulas
    // https://www.youtube.com/watch?v=HKFrz5P8D0g&list=PLGxZ4Rq3BOBpwaVgAPxTxhdX_TfSVlTcY&index=48
    /*
        ▸ Artigo do Martin Fowler: https://martinfowler.com/eaaCatalog/s...
        ▸ Livro: https://martinfowler.com/books/eaa.html
        ▸ YAGNI: https://martinfowler.com/bliki/Yagni....
        ▸ Discussão sobre services: https://softwareengineering.stackexch... when-only-one-class-will-ever-implement-it
        ▸ Artigo do Adam Bien: https://adambien.blog/roller/abien/en...
        https://www.baeldung.com/
        https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
        https://twitter.com/rponte
    */

    // @Autowired

    private final CourseService courseService;

    public CourseController( CourseService courseService) {
        this.courseService = courseService;
    }
    // coloca a propriedade no construtor para injeção de dependência
    // ou usar o lombook



    @GetMapping
    //public @ResponseBody List<CourseDTO> findAll() {
    public List<CourseDTO> findAll() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {

        return courseService.findById(id);
    }

    /* @PostMapping
     public ResponseEntity<Course> create(@RequestBody Course course) {

         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(courseRepository.save(course));
     }*/
    //forma alternativa
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid @NotNull CourseDTO course) {

        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable @NotNull @Positive Long id,
                            @RequestBody @Valid @NotNull CourseDTO course) {

        return courseService.update(id, course);
               /* .map(recordFound -> ResponseEntity.ok().body(recordFound)) //já faz return
                .orElse(ResponseEntity.notFound().build());*/
    }


/*
delete físico
hard delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {

        //veririca se existe na base
        return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }*/

    // delete lógico - soft delete
    //usando anotações do hibernet na classe Course
    //não precisa mudar o código aqui
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {

        courseService.delete(id);

        //veririca se existe na base
       /* if (courseService.delete(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();*/
    }

}
