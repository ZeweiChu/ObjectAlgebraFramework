package NewExample;

import library.Monoid;

public class SalaryBillMonoid implements Monoid<SalaryBill> {

	@Override
	public SalaryBill empty() {
		return () -> 0.0d;
	}

	@Override
	public SalaryBill join(SalaryBill x, SalaryBill y) {
		return () -> x.salaryBill() + y.salaryBill();
	}

}
