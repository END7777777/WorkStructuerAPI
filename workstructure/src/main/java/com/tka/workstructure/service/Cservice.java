package com.tka.workstructure.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.workstructure.dao.Cmaindao;
import com.tka.workstructure.entity.Country;

@Service
public class Cservice {

	@Autowired
	Cmaindao cdao;


	public String addcountry(Country c) {
		String msg = cdao.addcountry(c);
		if(Objects.isNull(msg)) {
			msg="Country is not added";
		}
		return msg;
	}


	public String updatecountry(Country c, int id) {
		String msg =cdao.updatecountry(c,id);
		if(Objects.isNull(msg)) {
			msg="Country is not updated";
		}
		return msg;
	}


	public String deletecountry(int id) {
		String msg=cdao.deletecountry(id);
		if(Objects.isNull(msg)) {
			msg="Country is deleted";
		}
		return msg;
	}


	public List<Country> getallcountry() {
		List<Country> list=cdao.getallcountry();
		return list;
	}


	public Country countrybyid(int id) {
		Country con=cdao.countrybyid(id);
		
		return con;
	}



	
}
