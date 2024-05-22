package com.example.basiccrud.service;

import com.example.basiccrud.service.spec.BookServiceSpec;

public interface BookService {

    BookServiceSpec.BookInfo create(BookServiceSpec.CreateBookRequest request);
    BookServiceSpec.BookInfo read(Long bookId);
    BookServiceSpec.BookInfo update(BookServiceSpec.UpdateBookRequest request);
    void delete(Long bookId);

}
