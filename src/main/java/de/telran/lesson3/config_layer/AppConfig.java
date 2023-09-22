package de.telran.lesson3.config_layer;

import de.telran.lesson3.domain_layer.database.DataBase;
import de.telran.lesson3.domain_layer.database.SimpleDataBase;
import de.telran.lesson3.repository_layer.CustomerRepository;
import de.telran.lesson3.repository_layer.ProductRepository;
import de.telran.lesson3.repository_layer.mysql.MySqlCustomerRepository;
import de.telran.lesson3.repository_layer.mysql.MySqlProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(requests -> requests
                                .requestMatchers(HttpMethod.GET, "/customer").permitAll()
                                .requestMatchers(HttpMethod.GET, "/product").permitAll()
                                .requestMatchers(HttpMethod.GET, "/product/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/customer/add/{customerId}/{productId}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/customer/delete/{customerId}/{productId}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/customer/clear/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/customer/{id}").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET, "/product/count").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET, "/customer/count").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET, "/product/total").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET, "/product/average").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET, "/customer/total/{id}").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET, "/customer/average/{id}").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.POST, "/customer").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/product/delete/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/product/deletename/{name}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/customer/delete/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/customer/deletename/{name}").hasRole("ADMIN")
                                .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults())
                        .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DataBase dataBase() {
        return new SimpleDataBase();
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new MySqlCustomerRepository();
    }

    @Bean
    public ProductRepository productRepository() {
        return new MySqlProductRepository();
    }

//    @Bean
//    public CustomerService customerService() {
//        return new CommonCustomerService();
//    }

//    @Bean
//    public ProductService productService() {
//        return new CommonProductService();
//    }
}