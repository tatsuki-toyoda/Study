package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@SpringBootTest
class UserServiceTest {

    // テスト対象
    @Autowired
    private UserService userService;

    // Repository をモックに差し替え
    @MockBean
    private UserRepository userRepository;

    @Test
    void searchAll_モックデータがそのまま返る() {

        // Step1: モックが返すダミーデータ
        UserEntity user1 = new UserEntity();
        user1.setId(1);
        user1.setName("花子");

        UserEntity user2 = new UserEntity();
        user2.setId(2);
        user2.setName("太郎");

        List<UserEntity> mockList = List.of(user1, user2);

        // Step2: findAll をモック
        when(userRepository.findAll()).thenReturn(mockList);

        // Step3: 実行
        List<UserEntity> result = userService.searchAll();

        // Step4: 検証
        assertEquals(2, result.size());
        assertEquals("花子", result.get(0).getName());
        assertEquals("太郎", result.get(1).getName());
    }
}
