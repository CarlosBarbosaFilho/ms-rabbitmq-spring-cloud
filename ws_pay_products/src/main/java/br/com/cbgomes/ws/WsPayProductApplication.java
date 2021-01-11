package br.com.cbgomes.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WsPayProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsPayProductApplication.class, args);
    }

}
