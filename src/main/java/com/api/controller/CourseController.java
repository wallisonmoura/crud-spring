package com.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.CourseDTO;
import com.api.dto.CoursePageDTO;
import com.api.dto.CourseRequestDTO;
import com.api.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  public CoursePageDTO findAll(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int pageSize) {
    return courseService.findAll(page, pageSize);
  }

  @GetMapping("/searchByName")
  public List<CourseDTO> findByName(@RequestParam @NotNull @NotBlank String name) {
    return courseService.findByName(name);
  }

  @GetMapping("/{id}")
  public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
    return courseService.findById(id);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CourseDTO create(@RequestBody @Valid CourseRequestDTO course) {
    return courseService.create(course);
  }

  @PutMapping("/{id}")
  public CourseDTO update(@PathVariable @NotNull @Positive Long id,
      @RequestBody @Valid @NotNull CourseRequestDTO course) {
    return courseService.update(id, course);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @NotNull @Positive Long id) {
    courseService.delete(id);
  }

}
