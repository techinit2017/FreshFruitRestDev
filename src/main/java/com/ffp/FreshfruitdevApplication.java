package com.ffp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FreshfruitdevApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreshfruitdevApplication.class, args);
		//ConfigurableApplicationContext context = SpringApplication.run(DBConfig.class);
		// DataSource dataSource = context.getBean("dataSource",
		// DataSource.class);

		System.out.println(">>>>>>>>> Starting of Project ");

		// ClassPathXmlApplicationContext context = new
		// ClassPathXmlApplicationContext("applicationContext.xml");
		//System.out.println(context.hashCode());

	}
}
