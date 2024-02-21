//package com.backend.safarnama.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.backend.safarnama.service.UserInfoUserDetailsService;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	
//	
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
////		UserDetails admin=User.withUsername("Safarnama").password(encoder.encode("Safar123")).roles("ADMIN").build();
////		UserDetails user=User.withUsername("Sarvesh").password("pass").roles("USER").build();
////		return new InMemoryUserDetailsManager(admin,user);
//		return new UserInfoUserDetailsService(null);
//	}
//	
//	@SuppressWarnings("removal")
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(requests -> requests.requestMatchers("/all-package").permitAll())
//                .authorizeHttpRequests(requests -> requests.requestMatchers("/add/hotel").authenticated()).formLogin(withDefaults()).build();
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//}
