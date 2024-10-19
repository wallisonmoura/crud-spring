package com.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.api.enums.Category;
import com.api.model.Course;
import com.api.model.Lesson;
import com.api.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.BACK_END);

			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeUrl("watch?v=1");
			l.setCourse(c);
			c.getLessons().add(l);
			courseRepository.save(c);
		};
	}
}
