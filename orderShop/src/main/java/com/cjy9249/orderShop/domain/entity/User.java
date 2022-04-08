package com.cjy9249.orderShop.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nickName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(nullable = false, columnDefinition = "smallint")
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String sex;

    /*@OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();*/

}
