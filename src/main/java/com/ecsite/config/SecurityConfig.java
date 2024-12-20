package com.ecsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRFを無効化（必要に応じて有効化）
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll() // 公開リソース
                .anyRequest().authenticated() // 他のリクエストは認証が必要
            )
            .formLogin(form -> form
                .loginPage("/login") // カスタムログインページ
                .defaultSuccessUrl("/", true) // ログイン成功時のリダイレクト
                .failureUrl("/login?error=true") // ログイン失敗時
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // パスワードのハッシュ化用
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // ユーザーデータの取得方法をカスタマイズする場合
        return username -> {
            // 仮のユーザー設定
            if ("user@example.com".equals(username)) {
                return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(passwordEncoder().encode("password"))
                    .roles("USER")
                    .build();
            }
            throw new RuntimeException("User not found");
        };
    }
}
