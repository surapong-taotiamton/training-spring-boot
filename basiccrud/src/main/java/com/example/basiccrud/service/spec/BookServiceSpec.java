package com.example.basiccrud.service.spec;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.NONE)
public class BookServiceSpec {


    @Accessors(chain = true)
    @Data
    public static class CreateBookRequest {
        private String bookName;
        private String isbn;
    }

    @Accessors(chain = true)
    @Data
    public static class UpdateBookRequest {
        private Long bookId;
        private String bookName;
        private String isbn;
    }

    @Accessors(chain = true)
    @Data
    public static class BookInfo {
        private Long bookId;
        private String bookName;
        private String isbn;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    }

}
