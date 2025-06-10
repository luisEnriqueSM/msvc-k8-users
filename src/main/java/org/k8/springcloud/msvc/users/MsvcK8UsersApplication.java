package org.k8.springcloud.msvc.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MsvcK8UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcK8UsersApplication.class, args);
	}

}
