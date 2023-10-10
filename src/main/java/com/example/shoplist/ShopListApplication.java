package com.example.shoplist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShopListApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ShopListApplication.class, args);
    }
}
