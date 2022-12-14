package com.spring.file.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.file.helper.CSVHelper;
import com.spring.file.model.Employee;

@Service
public class CSVservice {
	 public void save(MultipartFile file) {
		    try {
		      List<Employee> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
		     // repository.saveAll(tutorials);
		    } catch (IOException e) {
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
		    }
		  }

		  public ByteArrayInputStream load() {
			  Employee emp=new Employee(3,"khasim","dudekula","dudekula@gmail.com",234);
			  List<Employee> listEmp=new ArrayList<>();
			  listEmp.add(emp);
		   // List<Employee> tutorials = repository.findAll();

		    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(listEmp);
		    return in;
		  }

		  public List<Employee> getAllTutorials() {
			  Employee emp=new Employee(4,"khasim","dudekula","dudekula@gmail.com",234);
			  List<Employee> listEmp=new ArrayList<>();
			  listEmp.add(emp);
			 // repository.findAll();
		    return listEmp;
		  }

}
