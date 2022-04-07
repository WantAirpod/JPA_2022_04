package com.cjy9249.orderShop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name = "tb_order")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order{
    /* 주문번호 */
    @Id
    private String orderSrl;

    /* 제품명 */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String orderItem;

    /* 결제일시 */
    @Column(nullable = false)
    private LocalDateTime orderDate;

}
