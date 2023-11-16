package com.example.SimpleCRUDThymeleaf.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        @Bean
        MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
                return new MvcRequestMatcher.Builder(introspector);
        }

        /**
         * ! Defining which URL paths should be secured and which should not. The
         * defined paths are
         * ! configured to not require any authentication. All other paths require
         * authentication.
         * 
         * @param http
         * @return
         * @throws Exception
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc)
                        throws Exception {
                http
                                .authorizeHttpRequests((authorize) -> authorize
                                                .requestMatchers(mvc.pattern("/"))
                                                .permitAll()
                                                .requestMatchers(mvc.pattern("/index"))
                                                .permitAll()
                                                .requestMatchers(mvc.pattern("/signup"))
                                                .hasRole("ADMIN")
                                                .requestMatchers(antMatcher("/**/*.css"))
                                                .permitAll()
                                                .requestMatchers(antMatcher("/**/*.js"))
                                                .permitAll()
                                                .anyRequest().authenticated())
                                                
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .permitAll())
                                .logout((logout) -> logout.permitAll());

                return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                // Defining 3 users with differentes roles, usernames and password
                UserDetails user1 = User.withUsername("tito")
                                .password(passwordEncoder().encode("user1Pass"))
                                .roles("USER")
                                .build();
                UserDetails user2 = User.withUsername("rafa")
                                .password(passwordEncoder().encode("user2Pass"))
                                .roles("USER")
                                .build();
                UserDetails admin = User.withUsername("leudix96")
                                .password(passwordEncoder().encode("adminPass"))
                                .roles("ADMIN")
                                .build();
                return new InMemoryUserDetailsManager(user1, user2, admin);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}