package com.example.demo.service;

import com.example.demo.controller.request.CreateProductRequest;
import com.example.demo.controller.request.UpdateProductRequest;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
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

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Product findProduct(Long productNo) {
        return verifyProductExists(productNo);
    }

    @Transactional
    public Long deleteProduct(Long productNo) {
        verifyProductExists(productNo);
        productRepository.deleteById(productNo);
        return productNo;
    }

    @Transactional
    public Long updateProduct(UpdateProductRequest product) {
        Product foundProduct = verifyProductExists(product.getId());
        foundProduct.setKorCoNm(product.getKorCoNm());
        foundProduct.setFinPrdtNm(product.getFinPrdtNm());
        foundProduct.setSpclCnd(product.getSpclCnd());
        foundProduct.setJoinDeny(Product.JoinDeny.valueOf(product.getJoinDeny()));
        foundProduct.setDclsMonth(product.getDclsMonth());
        return foundProduct.getId();
    }

    @Transactional
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

    private Product verifyProductExists(Long productNo) {
        return productRepository.findById(productNo).orElseThrow(
                () -> new NoSuchElementException("Product does not exist"));
    }
}
