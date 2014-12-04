package sybDemo1;

import java.util.List;

//BEGIN_OOP_COMPANY
class Company {
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
class Salary {
	private Float salary;
	public Salary(Float salary){this.salary = salary;}
	public Float salaryBill(){return this.salary;}
	public void increaseSalary(){this.salary *= 1.1f;}
}
//END_OOP_COMPANY
class Department {
	private String name;
	private Employee manager;
	private List<SubUnit> subUnits;
	public Department(String name, List<SubUnit> subUnits, Employee manager){
		this.name = name;
		this.manager = manager;
		this.subUnits = subUnits;
	}
	public Float salaryBill(){
		Float r = 0f;
		for (SubUnit subUnit: this.subUnits) r += subUnit.salaryBill();
		r += this.manager.salaryBill();
		return r;
	}
	public void increaseSalary(){
		for (SubUnit subUnit: this.subUnits) subUnit.increaseSalary();
		this.manager.increaseSalary();
	}
}
class Employee {
	private Person person;
	private Float salary;
	public Employee(Person person, Float salary){
		this.person = person;
		this.salary = salary;
	}
	public Float salaryBill(){
		return this.salary;
	}
	public void increaseSalary(){
		this.salary *= 1.1f;
	}
}
class Person {
	private String name;
	private String address;
	public Person(String name, String address){
		this.name = name;
		this.address = address;
	}
}
class SubUnit {
	private Department dept;
	private Employee employee;
	public SubUnit(Department dept){
		this.dept = dept;
	}
	public SubUnit(Employee employee){
		this.employee = employee;
	}
	public Float salaryBill(){
		if (this.dept != null) return dept.salaryBill();
		else return employee.salaryBill();
	}
	public void increaseSalary(){
		if (this.dept != null) dept.increaseSalary();
		else if (this.employee != null) employee.increaseSalary();;
	}
}


