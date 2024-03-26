package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "dcls_month")
    private String dclsMonth;

    @Column(name = "kor_co_nm")
    private String korCoNm;

    @Column(name = "fin_prdt_nm")
    private String finPrdtNm;

    @Enumerated(EnumType.STRING)
    @Column(name = "join_deny")
    private JoinDeny joinDeny;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<JoinWay> joinWayList;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<Rate> rateList;

    public enum JoinDeny {
        제한없음, 서민전용, 일부제한
    }

    @Column(name = "spcl_cnd")
    private String spclCnd;
}
