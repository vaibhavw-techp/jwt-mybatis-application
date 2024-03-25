//package com.jwt.mybatis.jwtApplication.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
////@Configuration
////@EnableWebSecurity
////@EnableGlobalAuthentication
//public class AppConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails student1 = User.builder().username("vaibhav").password(passwordEncoder().encode("abcd")).roles("STUDENT").build();
//        UserDetails student2 = User.builder().username("parth").password(passwordEncoder().encode("abcd")).roles("STUDENT").build();
//        UserDetails admin1 = User.builder().username("pratik").password(passwordEncoder().encode("abcd")).roles("ADMIN").build();
//        UserDetails admin2 = User.builder().username("manjiri").password(passwordEncoder().encode("abcd")).roles("ADMIN").build();
//        UserDetails teacher1 = User.builder().username("saurabh").password(passwordEncoder().encode("abcd")).roles("TEACHER").build();
//        UserDetails teacher2 = User.builder().username("shubham").password(passwordEncoder().encode("abcd")).roles("TEACHER").build();
//
//        return new InMemoryUserDetailsManager(student1, student2, teacher1, teacher2, admin1, admin2);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
//        return builder.getAuthenticationManager();
//    }
//
//}
