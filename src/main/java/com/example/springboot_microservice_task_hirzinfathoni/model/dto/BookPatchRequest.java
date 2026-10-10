package com.example.springboot_microservice_task_hirzinfathoni.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookPatchRequest {

    private String title;
    private String author;
    private String isbn;
    private LocalDate publishedDate;
}
