package com.zewei.annotation.processor;

import java.util.List;
import javax.lang.model.type.*;

public class TransformExecutableTypeVisitor implements TypeVisitor<String, String[]>{
	@Override
	public String visitExecutable(ExecutableType t, String[] p) {
		List<? extends TypeMirror> lp = t.getParameterTypes();
		String[] lTypeArgs = p[1].substring(1, p[1].length()-1).split(",");
		String res = "\t@Override\n" 
					+ "\tdefault G_" + t.getReturnType() + " " + p[0] + "(";
		for (int i = 0; i < lp.size(); ++i){
			boolean flag = false;
			if (p[1].contains(lp.get(i).toString())) res += "G_";
			else {
				for (String s: lTypeArgs){
					if (lp.get(i).toString().contains(s)){
						res += lp.get(i).toString().replace(s, "G_"+s);
						flag = true;
						break;
					}
				}
			}
			if (flag == false) res += lp.get(i).toString();
			res += " p" + i;
			if (i < lp.size()-1) res += ", ";
		}
		res += ") {\n";
		res += "\t\treturn new G_" + t.getReturnType() + "() {\n"
				+ "\t\t\t@Override\n"
				+ "\t\t\tpublic " + p[1] + " " + t.getReturnType() + " accept(" + p[2] + p[1] + " alg) {\n";
		
		for (int i = 0; i < lp.size(); ++i){
			for (String s: lTypeArgs){
				if (lp.get(i).toString().contains(s) && lp.get(i).toString().contains("java.util.List")){
					//dirty trick for lists
					res += "\t\t\t\tjava.util.List<" + s + "> gp" + i + " = new java.util.ArrayList<" + s + ">();\n";
					res += "\t\t\t\tfor (G_" + s + " s: p" + i + ") {\n"
							+ "\t\t\t\t\tgp" + i + ".add(s.accept(alg));\n"
							+ "\t\t\t\t}\n";
					break;
				}
			}
		}
		res += "\t\t\t\treturn alg." + p[0] + "(";
		for (int i = 0; i < lp.size(); ++i){
			boolean flag = false;
			for (String s: lTypeArgs){
				if (lp.get(i).toString().contains(s) && lp.get(i).toString().contains("java.util.List")){
					res += "gp" + i;
					flag = true;
					break;
				}
			}
			if (flag == false) {
				res += "p" + i;
				if (p[1].contains(lp.get(i).toString())) res +=".accept(alg)";
			}
			if (i < lp.size()-1) res += ", ";
		}
		res += ");\n" 
				+"\t\t\t}\n";
		res += "\t\t};\n\t}\n";
		return res;
	}

	@Override
	public String visit(TypeMirror t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(TypeMirror t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitPrimitive(PrimitiveType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitNull(NullType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitArray(ArrayType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitDeclared(DeclaredType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitError(ErrorType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitTypeVariable(TypeVariable t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitWildcard(WildcardType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitNoType(NoType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitUnknown(TypeMirror t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitUnion(UnionType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitIntersection(IntersectionType t, String[] p) {
		// TODO Auto-generated method stub
		return null;
	}
}