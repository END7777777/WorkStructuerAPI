package com.tka.workstructure.controller;

import java.util.List;
import java.util.Map;

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

import com.tka.workstructure.entity.Employee;
import com.tka.workstructure.service.Eservice;

@RestController
@RequestMapping("EAPI")
public class Econtroller {

	@Autowired
	Eservice eservice;
	
	@PostMapping("addemp")
	public ResponseEntity<String> addemp(@RequestBody Employee emp){
		String msg=eservice.addemp(emp);
		return ResponseEntity.ok(msg);
		
	}
	
	@PutMapping("updateEmploye/{id}")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee emp, @PathVariable int id) {
		String msg = eservice.updateEmployee(emp, id);
		return ResponseEntity.ok(msg);
	}
	
	
	@DeleteMapping("DeleteEmp/{id}")
	public ResponseEntity<String> deletecountry(@PathVariable int id){
		String msg=eservice.deleteemp(id);
		return ResponseEntity.ok(msg);
	}
	
	@GetMapping("getALLEMP")
	public ResponseEntity<List<Employee>> getallemployee(){
		List<Employee> list=eservice.getallempployee();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("EMPbyID/{id}")
	public ResponseEntity<Employee> empbyid(@PathVariable int id){
		Employee emp=eservice.empbyid(id);
		return ResponseEntity.ok(emp);
	}
	
	@PostMapping("login")
	public ResponseEntity<Map> login(@RequestBody Employee emp) {
		Map map= eservice.login(emp);
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("salaryRange/{startSal}/{endSal}")
	public ResponseEntity<List<Employee>> salaryRange(@PathVariable double startSal,@PathVariable double endSal){
		List<Employee> list=eservice.salaryRange(startSal,endSal);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("EmployeStatus/{status}")
	public List<Employee> EmployeeStatus(@PathVariable String status) {
		List<Employee> list = eservice.EmployeeStatus(status);
		return list;
	
}
	

	@GetMapping("ChangeEmployeeStatus/{id}")
	public String ChangeEmployeeStatus(@PathVariable int id) {
		String msg = eservice.ChangeEmployeeStatus(id);
		return msg;
	}
	
	@GetMapping("GetEmployeesameCountry/{cid}")
	public  List<Employee> GetEmployeesameCountry(@PathVariable int cid) {
		 List<Employee> emp = eservice.GetEmployeesameCountry(cid);
		return emp;
	}
	



}
