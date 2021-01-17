package com.xiansi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.xiansi.mapper")
@EnableScheduling
public class XiansiluntanApplication {

	public static void main(String[] args) {
		SpringApplication.run(XiansiluntanApplication.class, args);
	}

}
