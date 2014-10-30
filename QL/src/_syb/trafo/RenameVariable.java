package _syb.trafo;

import java.util.Map;

import ql_obj_alg.check.types.Type;

public interface RenameVariable<E, S, F> extends ExpAlgId<E>, StmtAlgId<E, S>, FormAlgId<E, S, F> {
	default String rename(String old) {
		String newName = old;
		if (renaming().containsKey(old)) {
			newName = renaming().get(old);
			System.err.println("Renaming " + old + " to " + newName);
		}
		return newName;
	}
	
	Map<String, String> renaming();
	
	@Override 
	default E var(String varName) {
		return alg().var(rename(varName));
	}
	
	@Override 
	default S question(String id,String label,Type type) {
		return stmtAlg().question(rename(id), label, type);
	}
	
	@Override 
	default S question(String id,String label,Type type,E exp) {
		return stmtAlg().question(rename(id), label, type, exp);
	}
	
}
