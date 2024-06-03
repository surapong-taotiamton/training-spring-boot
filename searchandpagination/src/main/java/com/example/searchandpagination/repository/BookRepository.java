package com.example.searchandpagination.repository;

import com.example.searchandpagination.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByCategory(String category);

    Page<Book> findByCategory(String category, Pageable pageable);

    List<Book> findForHelloWorldByCategory(String category, Pageable pageable);

    @Query("SELECT t FROM Book t WHERE t.category = :category")
    Page<Book> findByCategoryByJPQL(@Param("category") String category, Pageable pageable);


    @Query("SELECT t FROM Book t WHERE t.category = :category  AND t.id > :moreThanId ")
    List<Book> findBookLikePantip(@Param("category") String category, @Param("moreThanId") Integer id,  Pageable pageable );

}
