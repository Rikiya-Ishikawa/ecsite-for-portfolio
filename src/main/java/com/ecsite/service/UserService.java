package com.ecsite.service;

import com.ecsite.model.User;
import com.ecsite.repository.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String email,String password) {
        register(email, password, "USER"); // デフォルトは "USER"
    }

    public void register(String email, String password, String role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userMapper.insertUser(user);
    }

    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public boolean existsAdminUser() {
        return userMapper.existsByRole("ADMIN");
    }
}
