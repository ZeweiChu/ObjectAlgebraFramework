package expDemo1;

/*
 * implement without object algebras
 */
public interface Exp {
	Value query();
	
	//for subst
	Exp subst(String v, Exp e);
}
