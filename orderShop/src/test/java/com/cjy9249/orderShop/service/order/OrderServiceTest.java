package com.cjy9249.orderShop.service.order;

import com.cjy9249.orderShop.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception{
        User user = new User();
        user.setName("야무치");
        user.setPassword("asdf");
        user.setNickName("야무치치");
        user.setSex("남");
        user.setPhoneNumber("010-22");
        user.setEmail("123@naver.com");
        user.setUserId(123L);
        em.persist(user);
    }




}