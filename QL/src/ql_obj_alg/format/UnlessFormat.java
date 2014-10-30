package ql_obj_alg.format;

import ql_obj_alg.box.IFormat;
import ql_obj_alg.syntax.IUnlessAlg;

public interface UnlessFormat extends StmtFormat, IUnlessAlg<IFormatWithPrecedence, IFormat>{
	default IFormat unless(IFormatWithPrecedence cond, java.util.List<IFormat> body) {
		return box().V(box().H(1,box().L("if"),box().H(box().L("("),cond,box().L(")")),box().L("{")),
				box().I(2,body), box().L("}"));
	}
}
