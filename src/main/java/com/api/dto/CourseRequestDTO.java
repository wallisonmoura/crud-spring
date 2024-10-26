package com.api.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.api.enums.Category;
import com.api.enums.validation.ValueOfEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CourseRequestDTO(
    @NotBlank @NotNull @Length(min = 4, max = 100) String name,
    @NotBlank @NotNull @ValueOfEnum(enumClass = Category.class) String category,
    @NotNull @NotEmpty @Valid List<LessonDTO> lessons) {
}
