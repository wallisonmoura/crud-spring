package com.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.enums.Status;
import com.api.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
  Page<Course> findByStatus(Pageable pageable, Status status);

  List<Course> findByName(String name);
}
