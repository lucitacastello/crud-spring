package com.acr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acr.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
