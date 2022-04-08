package com.cjy9249.orderShop.domain.repository.user;

import com.cjy9249.orderShop.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom{
    User findByUserId(Long userId);
}
