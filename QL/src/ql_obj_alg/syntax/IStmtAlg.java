package ql_obj_alg.syntax;

import java.util.List;

import com.zewei.annotation.processor.Algebra;

import noa.annos.Level;
import noa.annos.Syntax;
import ql_obj_alg.check.types.Type;

@Algebra
public interface IStmtAlg<Exp, Stm>  {
	@Syntax("stmt = 'if' '(' exp ')' '{' stmt* '}'") @Level(80)
	Stm iff(Exp cond, List<Stm> statements);
	
	@Syntax("stmt = 'if' '(' exp ')' '{' stmt* '}' 'else' '{' stmt* '}'") @Level(70)
	Stm iffelse(Exp cond, List<Stm> statementsIf, List<Stm> statementsElse);

	@Syntax("stmt = ID ':' STRING TYPE") 
	Stm question(String id, String label, Type type);
	
	@Syntax("stmt = ID ':' STRING TYPE '=' '(' exp ')'") 
	Stm question(String id, String label, Type type, Exp exp);
}
