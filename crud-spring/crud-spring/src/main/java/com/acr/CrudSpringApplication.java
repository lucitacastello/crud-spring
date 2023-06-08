package com.acr;

import com.acr.enuns.Category;
import com.acr.model.Lesson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.acr.model.Course;
import com.acr.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	//para exemplificar a listagem de cursos
	@Bean
	CommandLineRunner initDataBase(CourseRepository courseRepository){

		return args ->{
			courseRepository.deleteAll();
			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONT_END);

			Lesson lesson = new Lesson();
			lesson.setName("Introdução");
			lesson.setYoutubeUrl("youtube.com");
			lesson.setCourse(c);
			c.getLessons().add(lesson);

			Lesson lesson2 = new Lesson();
			lesson2.setName("Angular");
			lesson2.setYoutubeUrl("angular.com");
			lesson2.setCourse(c);
			c.getLessons().add(lesson2);

			courseRepository.save(c);

		};

	}

}
