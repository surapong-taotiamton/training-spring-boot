package com.example.searchandpagination.controller.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.NONE)
public class BookControllerDto {


    @Accessors(chain = true)
    @Data
    public static class SearchBookRequest {
        private String category;
        private int pageNo = 0;
        private int pageSize = 5;
    }


    @Accessors(chain = true)
    @Data
    public static class SearchBookResponse {
        private List<BookDto> data;
        private Integer total;
    }



    @Accessors(chain = true)
    @Data
    public static class SearchBookLikePantipRequest {
        private String category;
        private Integer moreThanId;
        private Integer limit;
    }


    @Accessors(chain = true)
    @Data
    public static class BookDto {
        private Integer id;
        private String title;
        private String author;
        private String category;
        private String subcategory;
        private Integer height;
        private String publisher;
    }


}
