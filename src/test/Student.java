package test;

import annotation.Column;
import annotation.Id;
import annotation.Table;

@Table
public class Student {
	
	@Id
	private Long uid;

	@Column(name="stu_name", length=10, unique=true, nullable=false)
	private String name;
	
	@Column
	private String address;
	
	
	
}
