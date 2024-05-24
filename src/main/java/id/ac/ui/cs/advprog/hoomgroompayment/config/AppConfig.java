package id.ac.ui.cs.advprog.hoomgroompayment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public double transactionAmount() {
        return 100.0;
    }
}

