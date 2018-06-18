package com.vlad.learn.notebook.core;

import com.vlad.learn.notebook.core.repository.JpaRepositoryMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@ComponentScan
@PropertySource("classpath:core.properties")
@EnableJpaRepositories(basePackageClasses = {JpaRepositoryMarker.class})
public class CoreConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}