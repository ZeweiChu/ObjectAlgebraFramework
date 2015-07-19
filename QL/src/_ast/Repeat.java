package _ast;

import java.util.Map;
import java.util.Set;

import library.Pair;
import ql_obj_alg.check.types.Type;

public class Repeat extends Stmt {

	private int count;
	private Stmt body;

	public Repeat(int n, Stmt body) {
		this.count = n;
		this.body = body;
  }
	
	
	@Override
	public Stmt rename(Map<String, String> ren) {
		return new Repeat(n, body.rename(ren));
	}

	@Override
	public Map<String, Type> typeEnv() {
		return body.typeEnv();
	}

	@Override
	public Set<Pair<String, String>> controlDeps() {
		return body.controlDeps();
	}

	@Override
	public Stmt flatten(Exp guard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stmt desugar() {
		// TODO Auto-generated method stub
		return null;
	}

}
