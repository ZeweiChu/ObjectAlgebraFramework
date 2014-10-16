package sybDemo1;

import java.util.List;

public class Department {
	private String name;
	private List<SubUnit> subUnits;
	public Department(String name, List<SubUnit> subUnits){
		this.name = name;
		this.subUnits = subUnits;
	}
	public Float salaryBill(){
		Float r = 0f;
		for (SubUnit subUnit: this.subUnits) r += subUnit.salaryBill();
		return r;
	}
	public void increaseSalary(){
		for (SubUnit subUnit: this.subUnits) subUnit.increaseSalary();
	}
	
	
	
}
