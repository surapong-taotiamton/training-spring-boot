package com.example.testlombok.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "password" })
public class UserDto {

    private String userId;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String contactNo;
    private String email;
    private int age;

//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
    private String remark;

//    public String getUsername() {
//        return this.username + "  SURAPONG ";
//    }

//    public void setUsername(String username) {
//        this.username = username.toLowerCase();
//    }
}
