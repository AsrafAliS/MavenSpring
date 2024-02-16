package com.project.controller;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gl.dbModel.Car;
import com.project.model.Employee;

@Controller
public class EmployeeController {
	
	
	@RequestMapping("/")
	public String Welcome() {
		
		return "welcome";
	}
	
	@RequestMapping("/gotoemployee")
	public String employeePage() {
		return "Employee";
	}
	
	@GetMapping("/addrecord")
	public String AddEmployee() {
		return"insert-employee";
	}
	
	
	@PostMapping("/saveEmployee")
	public String SaveEmployee(@RequestParam String employeename, @RequestParam String employeeaddress, 
	                           @RequestParam String employeephone, @RequestParam String employeeSalary,
	                           RedirectAttributes redirectAttributes) {
	    SessionFactory factory = new Configuration().configure().buildSessionFactory();
	    
	    try (Session session = factory.openSession()) {
	        Transaction tx = session.beginTransaction();
	        
	        Employee E1 = new Employee(0, employeename, employeeaddress, employeephone, employeeSalary);
	        session.save(E1);
	        
	        tx.commit();
	    } catch (Exception ex) {
	        System.out.println("Hibernate Error :" + ex.getMessage());
	    }

	    redirectAttributes.addFlashAttribute("successMessage", "Employee details saved successfully!");

	    // Redirect to the employee page
	    return "redirect:/showEmployee";
	}


	@RequestMapping("/showEmployee")
	public String ToShowTheEmployees(Model data) {
		
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		
		Session session=factory.openSession();
		

		try {
			
			Query q1=session.createQuery("from Employee");
			
			
			List<Employee> employees=q1.getResultList();
			data.addAttribute("employees",employees);
			
			
			
		
		
		
		}
		catch(Exception ex) {
			System.out.println("Hibernate Error :"+ex.getMessage());
		}
		
		return "Employee";
		
	}
	
	@GetMapping("/update")
	public String Update(@RequestParam int id,Model data) {
		
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		
		Session session=factory.openSession();
		

		try {
		
			Employee updateEmployee=session.get(Employee.class,id);
			data.addAttribute("employee",updateEmployee);
			
			
			
		
		
		
		}
		catch(Exception ex) {
			System.out.println("Hibernate Error :"+ex.getMessage());
		}
		
		return "update-employee";
	
	
	
	}
	
	@PostMapping("/updatedcontent")
	public String Updated(@RequestParam int id,
	                      @RequestParam String employeename,
	                      @RequestParam String employeeaddress,
	                      @RequestParam String employeephone,
	                      @RequestParam String employeeSalary,
	                      RedirectAttributes redirectAttributes) {
	    SessionFactory factory = new Configuration().configure().buildSessionFactory();

	    try (Session session = factory.openSession()) {
	        Transaction tx = session.beginTransaction();

	        // Get the existing employee from the database
	        Employee existingEmployee = session.get(Employee.class, id);
	        if (existingEmployee != null) {
	            // Update the existing employee details
	            existingEmployee.setEmployeename(employeename);
	            existingEmployee.setEmployeeaddress(employeeaddress);
	            existingEmployee.setEmployeephone(employeephone);
	            existingEmployee.setEmployeeSalary(employeeSalary);

	            // Update the employee in the database
	            session.update(existingEmployee);
	        }

	        tx.commit();
	    } catch (Exception ex) {
	        System.out.println("Hibernate Error :" + ex.getMessage());
	    }

	    redirectAttributes.addFlashAttribute("successMessage", "Employee details updated successfully!");

	    // Redirect to the employee page
	    return "redirect:/showEmployee";
	}

	

	@GetMapping("/Delete")
	public String deleteCar(@RequestParam int id,Model data,RedirectAttributes redirectAttributes) {

		SessionFactory factory=new Configuration().configure().buildSessionFactory();

		Session session=factory.openSession();


		try {
			
			Transaction tx=session.beginTransaction();
			Employee DeleteEmployee=new Employee(id,"","","", "");
			session.delete(DeleteEmployee);

	        tx.commit();

		}catch(Exception ex) {
			System.out.println("Hibernate error "+ex.getMessage());
		}
		 
		
		redirectAttributes.addFlashAttribute("successMessage", "Employee details updated successfully!");

		    // Redirect to the employee page
		    return "redirect:/showEmployee";
	}

}
