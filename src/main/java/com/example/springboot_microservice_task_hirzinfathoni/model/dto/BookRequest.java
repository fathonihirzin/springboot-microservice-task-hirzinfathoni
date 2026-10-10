package com.example.springboot_microservice_task_hirzinfathoni.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank(message = "Title can't be null")
    private String title;
    private String author;
    private String isbn;
    private LocalDate publishedDate;
}
