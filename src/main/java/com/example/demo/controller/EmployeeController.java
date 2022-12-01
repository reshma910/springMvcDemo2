package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository repo;
	
	@GetMapping({"/showEmployees","/","/list"})
	public ModelAndView showEmployees() {
		ModelAndView mav = new ModelAndView("list-employees");
		List<Employee> list = repo.findAll();
		mav.addObject("employees",list);
		return mav;
	}
	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView mav = new ModelAndView("add-employee-form");
		Employee s = new Employee();
		mav.addObject("employee",s);
		return mav;
		
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee) {
		repo.save(employee);
		return "redirect:/list";
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
		
		ModelAndView mav = new ModelAndView("add-employee-form");
		Employee emp = repo.findById(employeeId).get();
		mav.addObject("employee",emp);
		return mav;
		
	}
	
	@GetMapping("/showDeleteForm")
	public String showDeleteForm(@RequestParam Long employeeId) {
		repo.deleteById(employeeId);
		return "redirect:/list";
	}
	
}
