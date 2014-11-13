package sybDemo1;

//BEGIN_OOP_SALARY
public class Salary {
	private Float salary;
	public Salary(Float salary){this.salary = salary;}
	public Float salaryBill(){return this.salary;}
	public void increaseSalary(){this.salary *= 1.1f;}
}
//END_OOP_SALARY
