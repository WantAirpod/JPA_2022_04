package com.cjy9249.orderShop.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "tb_order")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long orderId;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; //주문 회원*/

    @Column(nullable = false, columnDefinition = "TEXT")
    private Long userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String orderSrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String productName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private LocalDateTime orderDt;

    /*//연관관계 메서드
    public void setUser(User user){
        this.user = user;
        user.getOrders().add(this);
    }*/
    public static Order createOrder(Long userId, String orderSrl, String productName){
        Order order = new Order();
        order.setUserId(userId); //주문자 아이디
        order.setOrderSrl(orderSrl); //주문 srl 넘버
        order.setProductName(productName); // 주문
        order.setOrderDt(LocalDateTime.now()); // 주문 시간 정보
        return order;
    }

}
