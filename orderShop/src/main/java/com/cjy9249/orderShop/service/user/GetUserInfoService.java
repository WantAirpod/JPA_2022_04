package com.cjy9249.orderShop.service.user;

import com.cjy9249.orderShop.domain.entity.User;
import com.cjy9249.orderShop.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class GetUserInfoService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUserInfo(int userId) {
        return userRepository.findByUserId(userId);
    }
}
