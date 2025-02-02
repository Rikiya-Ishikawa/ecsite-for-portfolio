package com.ecsite.config;

import com.ecsite.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {
    private final UserService userService;

    public AdminUserInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        String adminUsername = "admin";
        String adminPassword = "admin123";
        String adminEmail = "admin@example.com";

        // すでに管理者が存在する場合はスキップ
        if (!userService.existsAdminUser()) {
            userService.register(adminEmail, adminPassword, "ADMIN");
            System.out.println("管理者アカウントを作成しました: " + adminUsername);
        } else {
            System.out.println("管理者アカウントは既に存在します");
        }
    }
}
