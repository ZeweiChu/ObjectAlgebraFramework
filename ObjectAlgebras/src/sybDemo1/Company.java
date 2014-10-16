package sybDemo1;

import java.util.List;

public class Company {
	private List<Department> depts;
	public Company(List<Department> depts){this.depts = depts;}
	public Float salaryBill(){
		Float r = 0f; 
		for (Department dept: depts) r+= dept.salaryBill(); 
		return r;
	}
	public void increaseSalary(){
		for (Department dept: depts) dept.increaseSalary();
	}

}
