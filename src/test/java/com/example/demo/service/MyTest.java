package com.example.demo.service;

import com.example.demo.entity.JoinWay;
import com.example.demo.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MyTest {


    @Test
    public void findRatesTest() throws JsonProcessingException {
        Product product = new Product();
        product.setDclsMonth("a");
        product.setFinPrdtNm("b");
        product.setKorCoNm("c");

        List<JoinWay> joinWayList = new ArrayList<>();
        JoinWay joinWay = new JoinWay();
        joinWay.setName(JoinWay.JoinWayName.스마트폰);
        joinWayList.add(joinWay);
        product.setJoinWayList(joinWayList);

        String value = new ObjectMapper().writeValueAsString(product);
        System.out.println("value = " + value);
    }
}
