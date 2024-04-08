package com.example.connectdb;

import com.example.connectdb.dto.AddressDto;
import com.example.connectdb.dto.InsertAddressDto;
import com.example.connectdb.entity.Address;
import com.example.connectdb.entity.User;
import com.example.connectdb.repository.AddressRepository;
import com.example.connectdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/address/{userId}")
    public AddressDto getAddress(
            @PathVariable("userId") String userId
    ) {

        Address address = addressRepository.findById(userId).orElseThrow();

        return new AddressDto()
                .setUserId(address.getUserId())
                .setUsername(address.getUser().getUsername())
                .setHouseNo(address.getHouseNo())
                .setSubDistrict(address.getSubDistrict())
                .setDistrict(address.getDistrict())
                .setCity(address.getCity())
                .setPostcode(address.getPostcode());
    }


    @PostMapping("/address/insert")
    public AddressDto insertAddress(
            @RequestBody InsertAddressDto insertAddressDto
            ) {

        //User user = userRepository.findById(insertAddressDto.getUserId()).orElseThrow();

        Address address = new Address()
                .setUser(new User().setUserId(insertAddressDto.getUserId()))
                .setHouseNo(insertAddressDto.getHouseNo())
                .setSubDistrict(insertAddressDto.getSubDistrict())
                .setDistrict(insertAddressDto.getDistrict())
                .setCity(insertAddressDto.getCity())
                .setPostcode(insertAddressDto.getPostcode());

        address = addressRepository.save(address);

        return new AddressDto()
                .setUserId(address.getUserId())
                .setUsername(address.getUser().getUsername())
                .setHouseNo(address.getHouseNo())
                .setSubDistrict(address.getSubDistrict())
                .setDistrict(address.getDistrict())
                .setCity(address.getCity())
                .setPostcode(address.getPostcode());
    }


}
