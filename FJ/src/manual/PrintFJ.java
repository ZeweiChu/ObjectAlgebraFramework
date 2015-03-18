package manual;

import java.util.List;
import java.util.stream.Collectors;

import trees.FJAlg;

public class PrintFJ implements FJAlg<String, String, String, String, String, String, String, String> {
	public String Code(List<String> classes) {
		String res = "";
		for (String l : classes) res += l + "\n\n";
		return res;
	}

	public String ClassType(String className, List<String> sorts) {
		if (sorts.size() == 0) return className;
		return className + "<" + concatStr(sorts, ", ") + ">";
	}

	public String ClassDec(String className, List<Pair<String, String>> sorts,
			String superClass, List<Pair<String, String>> fields,
			String constr, List<String> methods) {
		String temp = concatStr(sorts.stream().map(x -> x.a() + " extends " + x.b).collect(Collectors.toList()), ", ");
		if (!temp.isEmpty()) temp = "<" + temp + ">";
		String res = "class " + className + temp + " extends " + superClass + " {\n";
		res += concatStr(fields.stream().map(x -> x.a() + " " + x.b + ";").collect(Collectors.toList()), "\n") + "\n" + constr + "\n" + concatStr(methods, "\n") + "\n}";
		return res;
	}

	public String ConstrDec(String className, List<Pair<String, String>> paras) {
		String res = className;
		res += "(" + concatStr(paras.stream().map(x -> x.a() + " " + x.b).collect(Collectors.toList()), ", ") + ") { super(-); this.- = -; }";
		return res;
	}

	public String MethodDec(List<Pair<String, String>> sorts,
			String returnType, String methodName,
			List<Pair<String, String>> paras, String returnStmt) {
		String temp = concatStr(sorts.stream().map(x -> x.a() + " extends " + x.b).collect(Collectors.toList()), ", ");
		if (!temp.isEmpty()) temp = "<" + temp + "> ";
		String res = temp;
		res += returnType + " " + methodName + "(" + concatStr(paras.stream().map(x -> x.a() + " " + x.b).collect(Collectors.toList()), ", ") + ") { return " + returnStmt + "; }";
		return res;
	}

	public String FieldAccess(String e, String fieldName) {
		return e + "." + fieldName;
	}

	public String MethodInvoke(String e, String methodName, List<String> sorts,
			List<String> paras) {
		String temp = concatStr(sorts, ", ");
		if (!temp.isEmpty()) temp = "<" + temp + ">";
		return e + "." + methodName + temp + "(" + concatStr(paras, ", ") + ")";
	}

	public String ObjectCreate(String className, List<String> paras) {
		return "new " + className + "(" + concatStr(paras, ", ") + ")";
	}

	public String Var(String name) { return name; }
	public String Cast(String castType, String e) { return "(" + castType + ")" + e; }
	public String TVar(String name) { return name; }
	public String TypeX(String x) { return x; }
	public String TypeN(String n) { return n; }
	
	private String concatStr(List<String> l, String symbol) {
		if (l.size() == 0) return "";
		String res = l.get(0);
		for (int i = 1; i < l.size(); i++)
			res += symbol + l.get(i);
		return res;
	}
	
}
