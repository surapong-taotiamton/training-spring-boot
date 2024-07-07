package com.example.testlombok;

import com.example.testlombok.controller.dto.InfoDto;

public class InfoWork {

    public static void main(String[] args) {

        InfoDto infoDto = new InfoDto("INFO_ID");

        System.out.println(infoDto.getInfoId());
        infoDto.setData1("DATA_1");

        System.out.println(infoDto);


        InfoDto infoDtoNew = new InfoDto("INFO_ID");
        infoDtoNew.setData1("DATA_1");
        System.out.println(infoDtoNew);

        System.out.println("-------- SECTION EQUAL AND HASHCODE --------");
        System.out.println(infoDto.hashCode());
        System.out.println(infoDtoNew.hashCode());
        System.out.println(infoDto.equals(infoDtoNew));

    }

}
