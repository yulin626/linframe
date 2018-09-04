package com.ganglion;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan({"com.ganglion.mapper"})
public class GanglionApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GanglionApiApplication.class, args);
    }
}