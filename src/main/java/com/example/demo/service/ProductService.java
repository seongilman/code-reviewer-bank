package com.example.demo.service;

import com.example.demo.controller.request.CreateProductRequest;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 모든 제품을 조회합니다.
     *
     * @return 제품 목록
     */
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    /**
     * 주어진 productNO 에 해당하는 상품을 조회합니다.
     *
     * @param productNo 상품 코드
     * @return 해당 상품
     * @throws NoSuchElementException 상품이 존재하지 않을 경우
     */
    public Product findProduct(Long productNo) throws JsonProcessingException {
        return verifyProductExists(productNo);
    }

    private Product verifyProductExists(Long productNo) {
        return productRepository.findById(productNo).orElseThrow(
                () -> new NoSuchElementException("Product does not exist"));
    }

    public Long createProduct(CreateProductRequest createProductRequest) {
        Product product = Product.builder()
                .joinDeny(Product.JoinDeny.valueOf(createProductRequest.getJoinDeny()))
                .spclCnd(createProductRequest.getSpclCnd())
                .finPrdtNm(createProductRequest.getFinPrdtNm())
                .korCoNm(createProductRequest.getKorCoNm())
                .dclsMonth(createProductRequest.getDclsMonth())
                .build();
        return productRepository.save(product).getId();
    }
}
