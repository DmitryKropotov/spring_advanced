package com.luxoft.springadvanced.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.springadvanced.services.Job;

@RestController
public class JobsController {
   @Autowired
   private Job job;
   
   @GetMapping("/")	
   public String getDescription() {
	   return job.getDescription();
   }
}
