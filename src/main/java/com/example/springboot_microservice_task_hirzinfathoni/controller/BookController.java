package com.example.springboot_microservice_task_hirzinfathoni.controller;

import com.example.springboot_microservice_task_hirzinfathoni.model.dto.BookPatchRequest;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.AllDataResponse;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.DefaultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.springboot_microservice_task_hirzinfathoni.model.dto.BookRequest;
import com.example.springboot_microservice_task_hirzinfathoni.model.dto.BookResponse;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.DataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springboot_microservice_task_hirzinfathoni.service.BookService;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<DataResponse<BookResponse>> create(@Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.create(bookRequest));
    }

    @GetMapping
    public ResponseEntity<AllDataResponse<BookResponse>> getAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<BookResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<BookResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.update(id, bookRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DataResponse<BookResponse>> patch(
            @PathVariable Long id,
            @Valid @RequestBody BookPatchRequest bookPatchRequest) {
        return ResponseEntity.ok(bookService.patch(id, bookPatchRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.delete(id));
    }
}
