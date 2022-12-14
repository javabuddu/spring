package com.spring.file.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private long id;
	private String name;
	private String lastName;
	private String email;
	private int phone;

}
