package com.example.basiccrud.service.impl;

import com.example.basiccrud.entity.Book;
import com.example.basiccrud.repository.BookRepository;
import com.example.basiccrud.service.BookService;
import com.example.basiccrud.service.spec.BookServiceSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookServiceSpec.BookInfo create(BookServiceSpec.CreateBookRequest request) {
        Book book = new Book()
                .setBookName(request.getBookName())
                .setIsbn(request.getIsbn());
        book = bookRepository.save(book);
        return toBookInfo(book);
    }

    @Override
    public BookServiceSpec.BookInfo read(Long id) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            return null;
        } else {
            return toBookInfo(book);
        }
    }

    @Override
    public BookServiceSpec.BookInfo update(BookServiceSpec.UpdateBookRequest request) {

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow( () -> new RuntimeException("Not found data for update"));

        book.setBookName(request.getBookName())
                .setIsbn(request.getIsbn());

        book = bookRepository.save(book);

        return toBookInfo(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    private BookServiceSpec.BookInfo toBookInfo(Book book) {
        return new BookServiceSpec.BookInfo()
                .setBookId(book.getBookId())
                .setBookName(book.getBookName())
                .setIsbn(book.getIsbn())
                .setCreateAt(book.getCreateAt())
                .setUpdateAt(book.getUpdateAt());
    }
}
