package transform;

import generic.G_EExp;
import generic.G_Stm;
import generic.G_Typ;
import trees.StmAlg;

public interface StmRename extends StmAlgTransform{
	@Override
	default G_EExp EVar(String v){
		return new G_EExp(){
			@Override
			public <Stm, Exp, Type> Exp accept(StmAlg<Stm, Exp, Type> alg) {
				return alg.EVar("_"+v);
			}
		};
	}
	@Override
	default G_Stm SDecl(G_Typ t, String v){
		return new G_Stm(){
			@Override
			public <Stm, Exp, Type> Stm accept(StmAlg<Stm, Exp, Type> alg) {
				return alg.SDecl(t.accept(alg), "_"+v);
			}
		};
	}
	@Override
	default G_Stm SAss(String v, G_EExp e){
		return new G_Stm(){
			@Override
			public <Stm, Exp, Type> Stm accept(StmAlg<Stm, Exp, Type> alg) {
				return alg.SAss("_"+v, e.accept(alg));
			}
		};
	}
}
