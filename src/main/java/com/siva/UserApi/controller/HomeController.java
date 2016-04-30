package com.siva.UserApi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.siva.UserApi.model.Employee;

@Controller
public class HomeController {


	private static List<Employee> employees;
	
	{
		//static code will run when server started
		//just to get set starting values
		//Try running http://localhost:8080/UserApi/employee?filter=Siva to test this
		employees = new ArrayList<Employee>();
		employees.add(new Employee("Siva"));
		employees.add(new Employee("Siva Periyasami"));
		employees.add(new Employee("Siva Sankar"));
		employees.add(new Employee("Jon"));
		employees.add(new Employee("David"));
	}
	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/employee",produces = "application/json")
	private @ResponseBody String filter(@RequestParam String filter) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> employeesFiltered = new ArrayList<Employee>();
		//you should write your own logic for searching
		for(Employee employee : employees){
			String name = employee.getName();
			if(name.contains(filter)){
				employeesFiltered.add(employee);
			}
		}
		return mapper.writeValueAsString(employeesFiltered);
	}
}
