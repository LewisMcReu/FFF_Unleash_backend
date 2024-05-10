package be.faros.flags;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(c -> c
                        .requestMatchers(HttpMethod.POST, "/flags").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/flags").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/flags").authenticated()
                        .anyRequest().permitAll()
                )
                .userDetailsService(new InMemoryUserDetailsManager(
                        User.withDefaultPasswordEncoder()
                                .username("sheldon")
                                .password("bazinga")
                                .build(),
                        User.withDefaultPasswordEncoder()
                                .username("leonard")
                                .password("bazinga")
                                .build(),
                        User.withDefaultPasswordEncoder()
                                .username("raj")
                                .password("bazinga")
                                .build(),
                        User.withDefaultPasswordEncoder()
                                .username("howard")
                                .password("bazinga")
                                .build()))
                .httpBasic(withDefaults())
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
