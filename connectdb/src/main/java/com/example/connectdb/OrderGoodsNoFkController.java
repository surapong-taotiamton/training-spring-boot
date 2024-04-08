package com.example.connectdb;

import com.example.connectdb.dto.*;
import com.example.connectdb.entity.OrderGoodsNoFk;
import com.example.connectdb.entity.OrderGoodsPk;
import com.example.connectdb.repository.OrderGoodsNoFkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGoodsNoFkController {

    @Autowired
    private OrderGoodsNoFkRepository orderGoodsNoFkRepository;

    @PostMapping("ordergoodsnofk/get")
    public OrderGoodsDto get(
            @RequestBody GetOrderGoodsDto getOrderGoodsDto
    ) {

        OrderGoodsNoFk orderGoodsNoFk  = orderGoodsNoFkRepository.findById(
                new OrderGoodsPk().setGoodsId(getOrderGoodsDto.getGoodsId())
                        .setOrderId(getOrderGoodsDto.getOrderId())
        ).orElseThrow();

        return new OrderGoodsDto()
                .setGoodsId(orderGoodsNoFk.getId().getGoodsId())
                .setOrderId(orderGoodsNoFk.getId().getOrderId())
                .setNumberOfPiece(orderGoodsNoFk.getNumberOfPiece())
                .setSum(orderGoodsNoFk.getSum())
                .setPricePerPiece(orderGoodsNoFk.getPricePerPiece());
    }

    @PostMapping("ordergoodsnofk/insert")
    public OrderGoodsDto insert(
            @RequestBody InsertOrderGoodsDto insertOrderGoodsDto
            ) {
        OrderGoodsNoFk orderGoodsNoFk = new OrderGoodsNoFk()
                .setId(new OrderGoodsPk()
                        .setOrderId(insertOrderGoodsDto.getOrderId())
                        .setGoodsId(insertOrderGoodsDto.getGoodsId())
                )
                .setSum(insertOrderGoodsDto.getSum())
                .setNumberOfPiece(insertOrderGoodsDto.getNumberOfPiece())
                .setPricePerPiece(insertOrderGoodsDto.getPricePerPiece());
        orderGoodsNoFk = orderGoodsNoFkRepository.save(orderGoodsNoFk);

        return new OrderGoodsDto()
                .setGoodsId(orderGoodsNoFk.getId().getGoodsId())
                .setOrderId(orderGoodsNoFk.getId().getOrderId())
                .setNumberOfPiece(orderGoodsNoFk.getNumberOfPiece())
                .setSum(orderGoodsNoFk.getSum())
                .setPricePerPiece(orderGoodsNoFk.getPricePerPiece());
    }


}
