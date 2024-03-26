package com.example.demo.controller;

import com.example.demo.controller.request.CreateProductRequest;
import com.example.demo.controller.response.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Rate;
import com.example.demo.service.ProductService;
import com.example.demo.service.RateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final RateService rateService;

    @PostMapping
    public Result createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return new Result(productService.createProduct(createProductRequest));
    }

    @GetMapping
    public Result listProduct() {
        List<Product> foundProducts = productService.findProducts();
        List<ProductDto> collect = foundProducts.stream()
                .map(p -> new ProductDto(
                        p.getId(),
                        p.getDclsMonth(),
                        p.getKorCoNm(),
                        p.getFinPrdtNm(),
                        p.getJoinDeny().name(),
                        p.getSpclCnd()
                ))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @GetMapping("/{id}")
    public Result findProduct(@PathVariable("id") Long id) throws JsonProcessingException {
        Product foundProducts = productService.findProduct(id);
        return new Result(foundProducts);
    }

    @GetMapping("/rates/{minRate}")
    public Result findByInitRateGreaterThan(@PathVariable("minRate") Double minRate) {
        List<Rate> rateList = rateService.findByInitRateGreaterThan(minRate);

        List<Map<String, String>> result = new ArrayList<>();

        for (Rate rate : rateList) {
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(rate.getProduct().getId()));
            map.put("korCoNm", rate.getProduct().getKorCoNm());
            map.put("finPrdtNm", rate.getProduct().getFinPrdtNm());
            map.put("dclsMonth", rate.getProduct().getDclsMonth());
            map.put("spclCnd", rate.getProduct().getSpclCnd());
            map.put("saveTrm", String.valueOf(rate.getSaveTrm()));
            map.put("initRate", String.valueOf(rate.getInitRate()));
            map.put("initRate2", String.valueOf(rate.getInitRate2()));

            result.add(map);
        }

        return new Result(result);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
