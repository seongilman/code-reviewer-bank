package com.example.demo.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateProductRequest {

    private String dclsMonth;
    private String korCoNm;
    private String finPrdtNm;
    public String joinDeny;
    private String spclCnd;
}
