package com.example.testmapstruct.service.spec;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ShopServiceSpec {


    @Accessors(chain = true)
    @Data
    public static class ShopInfo {
        private String shopId;
        private String shopName;
        private String owner;
    }


    @Accessors(chain = true)
    @Data
    public static class GoodsInfo {

    }


    @Accessors(chain = true)
    @Data
    public static class GoodsFullInfo {

    }


}
