package util;

import transform.CompanyAlgTransform;
import NewExample.CompanyAlg;

public class CompanyAlgTrans<A0, A1, A2, A3, A4, A5> implements CompanyAlgTransform<A0, A1, A2, A3, A4, A5> {

	private CompanyAlg<A0, A1, A2, A3, A4, A5> alg;

	public CompanyAlgTrans(CompanyAlg<A0, A1, A2, A3, A4, A5> alg) {this.alg = alg;}

	public CompanyAlg<A0, A1, A2, A3, A4, A5> companyAlg() {return alg;}

}