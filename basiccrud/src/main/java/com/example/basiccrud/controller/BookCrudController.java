package com.example.basiccrud.controller;

import com.example.basiccrud.controller.dto.BookCrudControllerDto;
import com.example.basiccrud.service.BookService;
import com.example.basiccrud.service.spec.BookServiceSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookCrudController {

    @Autowired
    private BookService bookService;

    @PostMapping("book/create")
    public BookCrudControllerDto.BookInfo create(
            @RequestBody BookCrudControllerDto.CreateBookRequest request
            ) {
        BookServiceSpec.BookInfo bookInfo = bookService.create(new BookServiceSpec.CreateBookRequest()
                .setBookName(request.getBookName())
                .setIsbn(request.getIsbn())
        );
        return  toBookInfo(bookInfo);
    }

    @PostMapping("book/read")
    public BookCrudControllerDto.BookInfo read(
            @RequestBody BookCrudControllerDto.ReadBookRequest request
    ) {
        BookServiceSpec.BookInfo bookInfo = bookService.read(request.getBookId());

        if (bookInfo == null) {
            return null;
        } else {
            return toBookInfo(bookInfo);
        }


    }

    @PostMapping("book/update")
    public BookCrudControllerDto.BookInfo update(
            @RequestBody BookCrudControllerDto.UpdateBookRequest request
    ) {

        BookServiceSpec.BookInfo bookInfo = bookService.update(new BookServiceSpec.UpdateBookRequest()
                .setBookId(request.getBookId())
                .setBookName(request.getBookName())
                .setIsbn(request.getIsbn())
        );
        return toBookInfo(bookInfo);
    }

    @PostMapping("book/delete")
    public void delete(
            @RequestBody BookCrudControllerDto.DeleteBookRequest request
    ) {
        bookService.delete(request.getBookId());
    }


    @GetMapping("book/rest-api/{bookId}")
    public BookCrudControllerDto.BookInfo readStyleRestApi(
            @PathVariable("bookId") Long bookId
    ) {

        BookServiceSpec.BookInfo bookInfo = bookService.read(bookId);
        if (bookInfo == null) {
            return null;
        } else {
            return toBookInfo(bookInfo);
        }


    }


    private BookCrudControllerDto.BookInfo toBookInfo(BookServiceSpec.BookInfo book) {
        return new BookCrudControllerDto.BookInfo()
                .setBookId(book.getBookId())
                .setBookName(book.getBookName())
                .setIsbn(book.getIsbn())
                .setCreateAt(book.getCreateAt())
                .setUpdateAt(book.getUpdateAt());
    }

}
