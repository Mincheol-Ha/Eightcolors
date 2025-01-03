package com.springbootfinal.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springbootfinal.app.mapper")
public class SpringbootfinalApp03Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootfinalApp03Application.class, args);
	}

}
