package com.example.demo.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateProductRequest {
    private Long id;
    private String dclsMonth;
    private String korCoNm;
    private String finPrdtNm;
    private String joinDeny;
    private String spclCnd;
}
