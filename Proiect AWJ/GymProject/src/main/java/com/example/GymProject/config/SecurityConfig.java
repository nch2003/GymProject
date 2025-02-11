/** Clasa pentru SecurityConfig
 * @author Dan Nicholas-Bogdan
 * @version 10 Decembrie 2024
 */
package com.example.GymProject.config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/", "/static/**", "/images/**","/register", "/login", "/logout", "/create-subscription", "/gyms", "/add_gym").permitAll()
                            .anyRequest().authenticated()
                    )
                    .formLogin(form -> form
                            .loginPage("/login")
                            .permitAll()
                            .failureUrl("/login?error=true")
                    )
                    .logout(config -> config
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/")
                            .permitAll()
                    )
                    .build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
