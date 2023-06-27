// package com.unisep.taskm.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Lazy;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

// @Configuration
// public class SecurityConfig {

//     @Autowired
//     @Lazy
//     private UserDetailsService userDetailsService;

//     @Bean
//     protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeHttpRequests((authz) -> authz
//                 .requestMatchers("/users/**").permitAll()
//                 .requestMatchers("/tsaks/**").permitAll()
//                 .anyRequest().authenticated()
//             )
//             .formLogin((form) -> form
//                 .successForwardUrl("/docs.html")
//                 .permitAll()
//             )
//             .logout((logout) -> logout
//                 .permitAll()
//             )
//             .csrf((csrf) -> csrf
//                 .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//             );
            
//         return http.build();
//     }

//     @Bean
//     protected InMemoryUserDetailsManager adminUserDetailsService() {
//         PasswordEncoder encoder = passwordEncoder();
//         UserDetails admin = User.builder()
//             .username("admin")
//             .password(encoder.encode("password"))
//             .roles("ADMIN")
//             .build();
//         return new InMemoryUserDetailsManager(admin);
//     }

//     @Bean
//     protected PasswordEncoder passwordEncoder() {
//         return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//     }

//     protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//         auth.userDetailsService(adminUserDetailsService());
//     }

// }
