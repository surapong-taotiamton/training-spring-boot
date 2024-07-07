package com.example.testlombok;

import com.example.testlombok.controller.dto.UserDto;

public class ChoreWork {

    public static void main(String[] args) {

        UserDto userDto = new UserDto();

        userDto.setUsername("HELLOWORLD");
        userDto.setPassword("P@ssw0rd");

        //System.out.println(userDto.getUsername());

        System.out.println(userDto);

    }

}
