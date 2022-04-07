
package com.cjy9249.orderShop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(name = "tb_user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nickName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String passward;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String sex;
}
