package com.example.springboot_microservice_task_hirzinfathoni.service.impl;

import com.example.springboot_microservice_task_hirzinfathoni.exception.custom.ResourceNotFoundException;
import com.example.springboot_microservice_task_hirzinfathoni.model.dto.BookPatchRequest;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.AllDataResponse;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.DefaultResponse;
import lombok.RequiredArgsConstructor;
import com.example.springboot_microservice_task_hirzinfathoni.model.dto.BookRequest;
import com.example.springboot_microservice_task_hirzinfathoni.model.dto.BookResponse;
import com.example.springboot_microservice_task_hirzinfathoni.model.entity.Book;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.DataResponse;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.ResponseMessage;
import org.springframework.stereotype.Service;
import com.example.springboot_microservice_task_hirzinfathoni.repository.BookRepository;
import com.example.springboot_microservice_task_hirzinfathoni.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;


    @Override
    public DataResponse<BookResponse> create(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setPublishedDate(bookRequest.getPublishedDate());

        Book saved = bookRepository.save(book);

        return new DataResponse<>(
                ResponseMessage.SUCCESS,
                ResponseMessage.DATA_CREATED,
                200,
                toResponse(saved)
        );
    }

    @Override
    public AllDataResponse<BookResponse> findAll() {
        List<BookResponse> books = bookRepository.findAll().stream()
                .map(this::toResponse)
                .toList();

        return new AllDataResponse<>(
                ResponseMessage.SUCCESS,
                ResponseMessage.DATA_FETCHED,
                200,
                books,
                books.size()
        );
    }

    @Override
    public DataResponse<BookResponse> findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));

        return new DataResponse<>(
                ResponseMessage.SUCCESS,
                ResponseMessage.DATA_FETCHED,
                200,
                toResponse(book)
        );
    }

    @Override
    public DataResponse<BookResponse> update(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));

        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setPublishedDate(bookRequest.getPublishedDate());

        Book saved = bookRepository.save(book);

        return new DataResponse<>(
                ResponseMessage.SUCCESS,
                ResponseMessage.DATA_UPDATED,
                200,
                toResponse(saved)
        );
    }

    @Override
    public DataResponse<BookResponse> patch(Long id, BookPatchRequest bookPatchRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));

        if (bookPatchRequest.getTitle() != null && !bookPatchRequest.getTitle().isBlank()) {
            book.setTitle(bookPatchRequest.getTitle());
        }
        if (bookPatchRequest.getAuthor() != null) {
            book.setAuthor(bookPatchRequest.getAuthor());
        }
        if (bookPatchRequest.getIsbn() != null) {
            book.setIsbn(bookPatchRequest.getIsbn());
        }
        if (bookPatchRequest.getPublishedDate() != null) {
            book.setPublishedDate(bookPatchRequest.getPublishedDate());
        }

        Book saved = bookRepository.save(book);

        return new DataResponse<>(
                ResponseMessage.SUCCESS,
                ResponseMessage.DATA_UPDATED,
                200,
                toResponse(saved)
        );
    }

    @Override
    public DefaultResponse delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND);
        }
        bookRepository.deleteById(id);

        return new DefaultResponse(
                ResponseMessage.SUCCESS,
                ResponseMessage.DATA_DELETED,
                200
        );
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublishedDate()
        );
    }
}
