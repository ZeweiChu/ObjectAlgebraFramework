package sybDemo1;

public class SubUnit {
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
