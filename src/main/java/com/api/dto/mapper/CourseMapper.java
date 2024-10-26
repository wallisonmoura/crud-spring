package com.api.dto.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.dto.CourseDTO;
import com.api.dto.CourseRequestDTO;
import com.api.dto.LessonDTO;
import com.api.enums.Category;
import com.api.model.Course;
import com.api.model.Lesson;

@Component
public class CourseMapper {

  public Course toModel(CourseRequestDTO courseRequestDTO) {
    Course course = new Course();
    course.setName(courseRequestDTO.name());
    course.setCategory(convertCategoryValue(courseRequestDTO.category()));

    Set<Lesson> lessons = courseRequestDTO.lessons().stream()
        .map(lessonDTO -> {
          Lesson lesson = new Lesson();
          if (lessonDTO._id() > 0) {
            lesson.setId(lessonDTO._id());
          }
          lesson.setName(lessonDTO.name());
          lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
          lesson.setCourse(course);
          return lesson;
        }).collect(Collectors.toSet());
    course.setLessons(lessons);

    return course;
  }

  public CourseDTO toDTO(Course course) {
    if (course == null) {
      return null;
    }
    List<LessonDTO> lessonDTOList = course.getLessons()
        .stream()
        .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
        .toList();
    return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(),
        lessonDTOList);
  }

  public Category convertCategoryValue(String value) {
    if (value == null) {
      return null;
    }
    return switch (value) {
      case "Front-end" -> Category.FRONT_END;
      case "Back-end" -> Category.BACK_END;
      default -> throw new IllegalArgumentException("Invalid Category.");
    };
  }

  public Lesson convertLessonDTOToLesson(LessonDTO lessonDTO) {
    Lesson lesson = new Lesson();
    lesson.setId(lessonDTO._id());
    lesson.setName(lessonDTO.name());
    lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
    return lesson;
  }

}
