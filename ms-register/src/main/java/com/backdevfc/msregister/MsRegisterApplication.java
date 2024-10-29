package com.backdevfc.msregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@EnableFeignClients("com.backdevfc.msregister")
@SpringBootApplication
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class MsRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsRegisterApplication.class, args);
    }

}
