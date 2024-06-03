package com.example.searchandpagination.controller;

import com.example.searchandpagination.controller.dto.BookControllerDto;
import com.example.searchandpagination.entity.Book;
import com.example.searchandpagination.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("book/search")
    public BookControllerDto.SearchBookResponse search(
            @RequestBody BookControllerDto.SearchBookRequest request
            ) {

        Sort sort = Sort.by("title", "author");
        //Sort sort = Sort.by(Sort.Direction.DESC, "title");
        Pageable pageable = PageRequest.of(request.getPageNo() -1 , request.getPageSize(), sort);
        Page<Book> resultSearch = bookRepository.findByCategory(request.getCategory(), pageable);

        BookControllerDto.SearchBookResponse searchBookResponse = new BookControllerDto.SearchBookResponse();

        searchBookResponse.setTotal((int)resultSearch.getTotalElements());
        searchBookResponse.setData(
                resultSearch.getContent().stream().map(book -> {
                    return toBookDto(book);
                }).collect(Collectors.toList())
        );

        return searchBookResponse;
    }


    @PostMapping("book/search-no-total")
    public  List<BookControllerDto.BookDto> searchNoTotal(
            @RequestBody BookControllerDto.SearchBookRequest request
    ) {

        Pageable pageable = PageRequest.of(request.getPageNo() -1 , request.getPageSize());
        List<Book> resultSearch = bookRepository.findForHelloWorldByCategory(request.getCategory(), pageable);

        return resultSearch.stream().map(book -> {
            return toBookDto(book);
        }).collect(Collectors.toList());
    }



    @PostMapping("book/search-by-jpql")
    public BookControllerDto.SearchBookResponse searchJpql(
            @RequestBody BookControllerDto.SearchBookRequest request
    ) {

        Sort sort = Sort.by("title", "author");
        //Sort sort = Sort.by(Sort.Direction.DESC, "title");
        Pageable pageable = PageRequest.of(request.getPageNo() -1 , request.getPageSize(), sort);
        Page<Book> resultSearch = bookRepository.findByCategoryByJPQL(request.getCategory(), pageable);

        BookControllerDto.SearchBookResponse searchBookResponse = new BookControllerDto.SearchBookResponse();

        searchBookResponse.setTotal((int)resultSearch.getTotalElements());
        searchBookResponse.setData(
                resultSearch.getContent().stream().map(book -> {
                    return toBookDto(book);
                }).collect(Collectors.toList())
        );

        return searchBookResponse;
    }



    @PostMapping("book/search-like-pantip")
    public  List<BookControllerDto.BookDto> searchLikePantip(
            @RequestBody BookControllerDto.SearchBookLikePantipRequest request
    ) {

        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(0 , request.getLimit(), sort);
        List<Book> resultSearch = bookRepository.findBookLikePantip(request.getCategory(), request.getMoreThanId(), pageable);

        return resultSearch.stream().map(book -> {
            return toBookDto(book);
        }).collect(Collectors.toList());
    }



    protected BookControllerDto.BookDto toBookDto(Book book) {
        return new BookControllerDto.BookDto()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor())
                .setHeight(book.getHeight())
                .setCategory(book.getCategory())
                .setSubcategory(book.getSubcategory())
                .setPublisher(book.getPublisher());
    }






}
