package com.ffp.controller.rest;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PingService {
	@RequestMapping("/ping")
	public String getServerTime(){
		System.out.println("Ping Service integraiton");
		return new Date().toString();
	}
}
