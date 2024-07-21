package com.example.testmapstruct.service.spec;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Accessors(chain = true)
@Data
public class ShopServiceSpec {


    @Accessors(chain = true)
    @Data
    public static class ShopInfo {
        private String shopId;
        private String shopName;
        private String shopAddress;
        private String shopEmail;
        private String shopContactNo;
        private String ownerFirstname;
        private String ownerLastname;
    }


    @Accessors(chain = true)
    @Data
    public static class UpdateShopRequest {
        private String shopId;
        private String shopName;
        private String shopAddress;
        private String shopEmail;
        private String shopContactNo;
    }



    @Accessors(chain = true)
    @Data
    public static class GoodsInfo {
        private String goodsId;
        private String goodsName;
        private BigDecimal price;
    }


    @Accessors(chain = true)
    @Data
    public static class GoodsFullInfo {
        private String goodsId;
        private String goodsName;
        private BigDecimal price;
        private String shopId;
        private String shopName;
        private String shopContactNo;
    }


}
