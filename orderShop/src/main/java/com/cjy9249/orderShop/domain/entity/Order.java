package com.cjy9249.orderShop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Table(name = "tb_user")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer orderId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String orderSrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String productName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String orderDt;


}
