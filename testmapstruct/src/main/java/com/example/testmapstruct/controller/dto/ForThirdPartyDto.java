package com.example.testmapstruct.controller.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ForThirdPartyDto {


    @Accessors(chain = true)
    @Data
    public static class ShopInfo {
        private String name;
        private String ownerFirstname;
        private String ownerLastname;
        private String contactNo;
        private String address;
        private String email;
    }


    @Accessors(chain = true)
    @Data
    public static class ShopInfoExternalData {
        private String name;
        private String ownerFirstname;
        private String ownerLastname;
        private String contactNo;
        private String address;
        private String email;
        private String taxId;
        private String country;
    }

}
