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

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String productName;

    @Column(nullable = false)
    private LocalDateTime orderDt;

    //추가
    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String name;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String email;

    public static Order createOrder(String productName){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = new Order();
        order.setProductName(productName);
        order.setOrderDt(LocalDateTime.now());
        if(principal!="anonymousUser"){
            UserDetailsImpl userDetails = (UserDetailsImpl)principal;
            order.setName(userDetails.getUsername());
            order.setEmail(userDetails.getEmail());
        }else {
            order.setEmail("anonymousUser@google.com");
            order.setName((String)principal);
        }
        return order;
    }

}
