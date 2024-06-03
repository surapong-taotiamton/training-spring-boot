package com.example.searchandpagination.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Data
@Table(name = "book")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private String category;
    private String subcategory;
    private Integer height;
    private String publisher;
}