package com.tka.workstructure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.workstructure.entity.Country;
import com.tka.workstructure.service.Cservice;

@RestController
@RequestMapping("API")
public class Ccontroller {

	@Autowired
	Cservice service;
	
	@PostMapping("addcountry")
	public ResponseEntity<String> addcountry(@RequestBody Country c){
		String msg=service.addcountry(c);
		return ResponseEntity.ok(msg);
		
	}
	
	@PutMapping("updatecountry/{id}")
	public ResponseEntity<String> updatecountry(@RequestBody Country c, @PathVariable int id){
		String msg=service.updatecountry(c,id);
		return ResponseEntity.ok(msg);
	}
	
	@DeleteMapping("deletecountry/{id}")
	public ResponseEntity<String> deletecountry(@PathVariable int id){
		String msg=service.deletecountry(id);
		return ResponseEntity.ok(msg);
	}
	
	@GetMapping("getallcountry")
	public ResponseEntity<List<Country>> getallcountry(){
		List<Country> list =service.getallcountry();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("CountryById/{id}")
	public ResponseEntity<Country> countrybyid(@PathVariable int id){
		Country con=service.countrybyid(id);
		return ResponseEntity.ok(con);
		
	}
}
