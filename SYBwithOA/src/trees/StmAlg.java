package trees;

import java.util.List;
import com.zewei.annotation.processor.Algebra;

@Algebra
public interface StmAlg<Stm, EExp, Typ>{
	Stm SDecl(Typ t, String v);
	Stm SAss(String v, EExp e);
	Stm SBlock(List<Stm> ls);
	Stm SReturn(EExp e);
	EExp EStm(Stm s);
	EExp EAdd(EExp exp1, EExp exp2);
	EExp EVar(String v);
	EExp EInt(int i);
	Typ TInt();
	Typ TFloat();
}

