package com.cjy9249.orderShop.domain.entity;

import com.cjy9249.orderShop.service.security.UserDetailsImpl;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @Column(nullable = false, columnDefinition = "TEXT")
    private String productName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private LocalDateTime orderDt;

    //추가
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String email;

    public static Order createOrder(String productName){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl)principal;

        Order order = new Order();
        order.setProductName(productName);
        order.setOrderDt(LocalDateTime.now());
        //추가
        order.setName(userDetails.getUsername());
        order.setEmail(userDetails.getEmail());
        return order;
    }

}
