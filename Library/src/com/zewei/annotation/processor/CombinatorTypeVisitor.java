package com.zewei.annotation.processor;

import java.util.List;

import javax.lang.model.type.*;

public class CombinatorTypeVisitor implements TypeVisitor<String, String[]> {

	int arrayContains(String[] ls, String s){
		int i = 0;
		for (String ts: ls){
			if (s.contains(ts)) return i;
			i++;
		}
		return -1;
	}
	
	@Override
	public String visitExecutable(ExecutableType t, String[] p) {
		String methodName = p[0];
		String[] lTypeArgs = p[1].split(",");
		List<? extends TypeMirror> lp = t.getParameterTypes();
		int flag;
		
		String res = "\tpublic Pair<A, B> " + methodName + "(";
		String resPart1 = "";
		String resPart2 = "";
		
		for (int i = 0; i < lp.size(); ++i){
			flag = arrayContains(lTypeArgs, lp.get(i).toString());
			if (flag != -1 && lp.get(i).toString().contains("java.util.List"))	{
				res += "List<Pair<A, B>> p" + i;
				resPart1 += "getPairList(p" + i + ").a()";
				resPart2 += "getPairList(p" + i + ").b()";
			}
			else if (flag != -1) {
				res +=  "Pair<A, B> p" + i;
				resPart1 += "p" + i + ".a()";
				resPart2 += "p" + i + ".b()";
			}
			else {
				res += lp.get(i).toString() + " p" + i;
				resPart1 += "p" + i;
				resPart2 += "p" + i;
			}
			if (i < lp.size()-1) {
				res += ", ";
				resPart1 += ", ";
				resPart2 += ", ";
			}
		}
		
		res += ") {\n\t\treturn new Pair<A, B>(q1." + methodName + "(";
		res += resPart1;
		res += "), q2." + methodName + "(";
		res += resPart2;		
		res += "));\n\t}\n\n";
		
		return res;
	}
	
	@Override
	public String visit(TypeMirror arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(TypeMirror arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitArray(ArrayType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDeclared(DeclaredType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitError(ErrorType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitIntersection(IntersectionType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitNoType(NoType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitNull(NullType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitPrimitive(PrimitiveType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitTypeVariable(TypeVariable arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitUnion(UnionType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitUnknown(TypeMirror arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitWildcard(WildcardType arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
