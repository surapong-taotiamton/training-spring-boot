package com.example.connectdb;

import com.example.connectdb.dto.GetOrderGoodsDto;
import com.example.connectdb.dto.InsertOrderGoodsDto;
import com.example.connectdb.dto.OrderGoodsDto;
import com.example.connectdb.entity.*;
import com.example.connectdb.repository.OrderGoodsWithFkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGoodsWithFkController {

    @Autowired
    private OrderGoodsWithFkRepository orderGoodsWithFkRepository;

    @PostMapping("ordergoodswithfk/get")
    public OrderGoodsDto get(
            @RequestBody GetOrderGoodsDto getOrderGoodsDto
    ) {

        OrderGoodsWithFk orderGoodsWithFk  = orderGoodsWithFkRepository.findById(
                new OrderGoodsPk().setGoodsId(getOrderGoodsDto.getGoodsId())
                        .setOrderId(getOrderGoodsDto.getOrderId())
        ).orElseThrow();

        return new OrderGoodsDto()
                .setGoodsId(orderGoodsWithFk.getId().getGoodsId())
                .setOrderId(orderGoodsWithFk.getId().getOrderId())
                .setNumberOfPiece(orderGoodsWithFk.getNumberOfPiece())
                .setSum(orderGoodsWithFk.getSum())
                .setPricePerPiece(orderGoodsWithFk.getPricePerPiece());
    }


    @PostMapping("ordergoodswithfk/insert")
    public OrderGoodsDto insert(
            @RequestBody InsertOrderGoodsDto insertOrderGoodsDto
    ) {
        OrderGoodsWithFk orderGoodsWithFk = new OrderGoodsWithFk()
                .setId(new OrderGoodsPk())
                .setGoods(new Goods().setGoodsId(insertOrderGoodsDto.getGoodsId()))
                .setOrder(new Order().setOrderId(insertOrderGoodsDto.getOrderId()))
                .setSum(insertOrderGoodsDto.getSum())
                .setNumberOfPiece(insertOrderGoodsDto.getNumberOfPiece())
                .setPricePerPiece(insertOrderGoodsDto.getPricePerPiece());
        orderGoodsWithFk = orderGoodsWithFkRepository.save(orderGoodsWithFk);

        return new OrderGoodsDto()
                .setGoodsId(orderGoodsWithFk.getId().getGoodsId())
                .setOrderId(orderGoodsWithFk.getId().getOrderId())
                .setNumberOfPiece(orderGoodsWithFk.getNumberOfPiece())
                .setSum(orderGoodsWithFk.getSum())
                .setPricePerPiece(orderGoodsWithFk.getPricePerPiece());
    }


}
