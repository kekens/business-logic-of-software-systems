package com.ifelseelif.blsslab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class BlssLab1Application {

    public static void main(String[] args) {
        SpringApplication.run(BlssLab1Application.class, args);
    }
}
