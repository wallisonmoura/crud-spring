package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
