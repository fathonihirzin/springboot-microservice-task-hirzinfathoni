package com.example.springboot_microservice_task_hirzinfathoni.service;

import com.example.springboot_microservice_task_hirzinfathoni.model.dto.BookPatchRequest;
import com.example.springboot_microservice_task_hirzinfathoni.model.dto.BookRequest;
import com.example.springboot_microservice_task_hirzinfathoni.model.dto.BookResponse;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.AllDataResponse;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.DataResponse;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.DefaultResponse;

public interface BookService {
    DataResponse<BookResponse> create(BookRequest bookRequest);

    AllDataResponse<BookResponse> findAll();

    DataResponse<BookResponse> findById(Long id);

    DataResponse<BookResponse> update(Long id, BookRequest bookRequest);

    DataResponse<BookResponse> patch(Long id, BookPatchRequest bookPatchRequest);

    DefaultResponse delete(Long id);
}
