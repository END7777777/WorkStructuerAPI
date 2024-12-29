package com.tka.workstructure.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.workstructure.dao.Edao;
import com.tka.workstructure.entity.Employee;

@Service
public class Eservice {

	@Autowired
	Edao edao;

	public String addemp(Employee emp) {
		String msg=edao.addemp(emp);
		if(Objects.isNull(msg)) {
			msg="Employee is not added...";
		}
		return msg;
	}

	public String updateEmployee(Employee emp, int id) {
		String msg = edao.updateEmployee(emp, id);
		if (Objects.isNull(emp)) {
			msg = "Employe is not updated";
		}
		return msg;
	}


	public String deleteemp(int id) {
		String msg=edao.deleteemp(id);
		if(Objects.isNull(msg)) {
			msg="Employee is not deleted";
		}
		return msg;
	}

	public List<Employee> getallempployee() {
		List<Employee> list=edao.getallemployee();
		return list;
	}

	public Employee empbyid(int id) {
	Employee emp=edao.empbyid(id);
		return emp;
	}

public Map login(Employee emp) {
		
		Employee obj= edao.login(emp);
		Map map=new HashMap();
		if(Objects.isNull(obj)) {
			map.put("msg", "Invalid User");
			map.put("user", obj);
		}else {
			map.put("msg", "Valid User");
			map.put("user", obj);
		}
		
		return map;
	}

	public List<Employee> salaryRange(double startSal, double endSal) {
	
			List<Employee> list= edao.salaryRange(startSal,endSal);
		
		return list;
	}

	public List<Employee> EmployeeStatus(String status) {
		List<Employee>list=edao.EmployeeStatus(status);
		return list;
	}
	
	public String ChangeEmployeeStatus(int id) {
		String msg = edao.ChangeEmployeeStatus(id);
		return msg;
	}

	public  List<Employee> GetEmployeesameCountry(int country) {
		 List<Employee> emp =edao.GetEmployeesameCountry(country);
		 return emp;
	}


}
