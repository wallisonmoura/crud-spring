package com.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CourseDTO(
    @JsonProperty("_id") Long id,
    String name,
    String category,
    List<LessonDTO> lessons) {
}
