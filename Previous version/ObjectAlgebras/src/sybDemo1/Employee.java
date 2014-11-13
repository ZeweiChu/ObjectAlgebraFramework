package sybDemo1;

public class Employee {
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
