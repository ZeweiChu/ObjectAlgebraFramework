package sybDemo1;

public class Manager {
	private Employee employee;
	public Manager(Employee employee){
		this.employee = employee;
	}
	public Float salaryBill(){
		return this.employee.salaryBill();
	}
	public void increaseSalary(){
		this.employee.increaseSalary();
	}
}
