package com.api.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.api.enums.Category;
import com.api.enums.Status;
import com.api.model.Course;
import com.api.model.Lesson;
import com.api.repository.CourseRepository;

@ActiveProfiles("test")
@DataJpaTest
@SuppressWarnings("null")
public class CourseRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private CourseRepository courseRepository;

  @Test
  @DisplayName("Should find all courses in the database by Status with pagination")
  void testFindAllByStatus() {
    Course course = createValidCourse();

    entityManager.persist(course);
    Page<Course> coursePage = courseRepository.findByStatus(PageRequest.of(0, 5), Status.ACTIVE);

    assertThat(coursePage).isNotNull();
    assertThat(coursePage.getContent()).isEmpty();
    assertThat(coursePage.getContent().get(0).getLessons()).isNotEmpty();
  }

  private Course createValidCourse() {
    Course course = new Course();
    course.setName("Spring");
    course.setCategory(Category.BACK_END);

    Lesson lesson = new Lesson();
    lesson.setName("Lesson 1");
    lesson.setYoutubeUrl("abcdefgh123");
    lesson.setCourse(course);
    course.setLessons(Set.of(lesson));

    return course;
  }
}
