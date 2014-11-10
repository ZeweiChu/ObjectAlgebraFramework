package com.zewei.annotation.processor;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes(value={"com.zewei.annotation.processor.Algebra"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class AlgebraProcessor extends AbstractProcessor {

	private Filer filer;
	
	@Override
	public void init(ProcessingEnvironment env){
		filer = env.getFiler();
	}
	
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment env) {
		String folder = null;
		String classContent = null;
		String algName;
		JavaFileObject jfo = null;
		for (Element element: env.getElementsAnnotatedWith(Algebra.class)){			
			
			// Avoid infinite loop.
			if (element.getSimpleName().toString().startsWith("G_")){
				continue;
			}

			// Initialization.
			TypeMirror tm = element.asType();
			String typeArgs = tm.accept(new DeclaredTypeVisitor(), element);
			String[] lTypeArgs = toList(typeArgs);
			algName = element.getSimpleName().toString();
			
			// Create transform classes "AlgNameTransform".
			folder = "transform";
			classContent = createTransformClass(folder, element, lTypeArgs, typeArgs);
			jfo = null;
			try{
				jfo = filer.createSourceFile(folder + "/" + element.getSimpleName()+"Transform", element);
				jfo.openWriter().append(classContent).close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
			
		    // Create query classes "AlgNameQuery".
			folder = "query";
			classContent = createQueryClass(folder, element, lTypeArgs, typeArgs);
			jfo = null;
			try{
				jfo = filer.createSourceFile(folder + "/" + algName + "Query", element);
				jfo.openWriter().append(classContent).close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
			// Create query classes "G_AlgNameQuery".
			folder = "query";
			classContent = createGeneralQueryClass(folder, element, lTypeArgs, typeArgs);
			jfo = null;
			try{
				jfo = filer.createSourceFile(folder + "/G_" + algName + "Query", element);
				jfo.openWriter().append(classContent).close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
			// Create combinator classes "Combine***".
			folder = "query";
			classContent = createCombinatorClass(folder, element, typeArgs);
			jfo = null;
			try{
				jfo = filer.createSourceFile(folder + "/Combine" + algName, element);
				jfo.openWriter().append(classContent).close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
		}
		return true;
		
	}
	
	private String[] toList(String message) {
		return message.split(",");
	}

	@Override
	public SourceVersion getSupportedSourceVersion(){
		return SourceVersion.latestSupported();
	}
	
	private String getPackage(Element element) {
		return ((PackageElement)element.getEnclosingElement()).getQualifiedName().toString();
	}
	
	String createTransformClass(String folder, Element element, String[] lTypeArgs, String typeArgs){
		List<? extends Element> le = element.getEnclosedElements();
		String algName = element.getSimpleName().toString();
		String className = algName + "Transform";
		String classContent = "package " + folder + ";\n\n";
		String typeArguments = "<";
		for (int i = 0; i < lTypeArgs.length; i++) {
			typeArguments += "A" + i;
			if (i < lTypeArgs.length - 1) typeArguments += ", ";
		}
		typeArguments += ">";
		classContent += "import " + getPackage(element) + "." + algName + ";\n\n";
		classContent += "public class " + className + typeArguments + " implements " + algName + typeArguments + " {\n\n";
		classContent += "\tpublic " + algName + typeArguments + " alg;\n\n";
		classContent += "\tpublic " + className + "(" + algName + typeArguments + " alg) { this.alg = alg; }\n\n";					
		for (Element e: le){
			String methodName = e.getSimpleName().toString();
			String[] args = {methodName, typeArgs};
			classContent += e.asType().accept(new TransformExecutableTypeVisitor(), args);
		}
		classContent += "}";
		return classContent;
	}
	
	String createQueryClass(String folder, Element element, String[] lTypeArgs, String typeArgs) {
		String algName = element.getSimpleName().toString();
		String classContent = "package " + folder + ";\n\n"
				+ "import library.Monoid;\n"
				+ "import " + getPackage(element) + "." + element.getSimpleName() + ";\n\n" 
				+ "public class " + algName + "Query<R> implements " + algName + "<";
		for (int i = 0; i < lTypeArgs.length; i++){
			classContent += "R";
			if (i < lTypeArgs.length-1) classContent += ", ";
		}
		classContent += "> {\n\n" + 
				"\tprivate Monoid<R> m;\n\n\tpublic Monoid<R> m() { return m; }\n\n\tpublic " + algName + "Query(Monoid<R> m) { this.m = m; }\n\n";
		List<? extends Element> le = element.getEnclosedElements();
		for (Element e: le){
			String methodName = e.getSimpleName().toString();
			String[] args = {methodName, typeArgs};
			classContent += e.asType().accept(new QueryExecutableTypeVisitor(), args);
		}
		classContent += "}";
		return classContent;
	}
	
	String createGeneralQueryClass(String folder, Element element, String[] lTypeArgs, String typeArgs) {
		String algName = element.getSimpleName().toString();
		String typeArguments = "<";
		String[] monoidList = new String[lTypeArgs.length];
		for (int i = 0; i < lTypeArgs.length; i++) {
			typeArguments += "A" + i;
			if (i < lTypeArgs.length - 1) typeArguments += ", ";
			monoidList[i] = "Monoid<A" + i + "> m" + i;
		}
		typeArguments += ">";
		String classContent = "package " + folder + ";\n\n"
				+ "import library.Monoid;\n"
				+ "import " + getPackage(element) + "." + element.getSimpleName() + ";\n\n" 
				+ "public class G_" + algName + "Query" + typeArguments + " implements " + algName + typeArguments + " {\n\n";
		for (int i = 0; i < lTypeArgs.length; i++) {
			classContent += "\tprivate " + monoidList[i] + ";\n";
		}
		classContent += "\n\tpublic G_" + algName + "Query("; 
		for (int i = 0; i < lTypeArgs.length; i++) {
			classContent += monoidList[i];
			if (i < lTypeArgs.length - 1) classContent += ", ";
		}
		classContent += ") {\n";
		for (int i = 0; i < lTypeArgs.length; i++) {
			classContent += "\t\tthis.m" + i + " = m" + i + ";\n";
		}
		classContent += "\t}\n\n";
		List<? extends Element> le = element.getEnclosedElements();
		for (Element e: le){
			String methodName = e.getSimpleName().toString();
			String[] args = {methodName, typeArgs};
			classContent += e.asType().accept(new GeneralQueryTypeVisitor(), args);
		}
		classContent += "}";
		return classContent;
	}
	
	String createCombinatorClass(String folder, Element element, String typeArgs) {
		String algName = element.getSimpleName().toString();
		String className = "Combine" + algName;
		int typeNum = toList(typeArgs).length;
		String alg1 = "";
		String alg2 = "";
		for (int i = 0; i < typeNum; i++) {
			if (i > 0) {
				alg1 += ", ";
				alg2 += ", ";
			}
			alg1 += "A" + i;
			alg2 += "B" + i;
		}
		String classContent = "package " + folder + ";\n\n"
				+ "import java.util.ArrayList;\n"
				+ "import java.util.List;\n"
				+ "import library.Pair;\n"
				+ "import " + getPackage(element) + "." + element.getSimpleName() + ";\n\n" 
				+ "public class " + className + "<" + alg1 + ", " + alg2 + ">\n\timplements " + algName + "<";
		for (int i = 0; i < typeNum; i++) {
			if (i > 0) classContent += ", ";
			classContent += "Pair<A" + i + ", B" + i + ">";
		}
		classContent += "> {\n\n"
				+ "\tpublic " + algName + "<" + alg1 + "> alg1;\n\tpublic " + algName + "<" + alg2 + "> alg2;\n\n"
				+ "\tpublic " + className + "(" + algName + "<" + alg1 + "> _alg1, " + algName + "<" + alg2 + "> _alg2) {\n"
				+ "\t\talg1 = _alg1;\n\t\talg2 = _alg2;\n\t}\n\n"
				+ "\tprivate <A, B> Pair<List<A>, List<B>> getPairList(List<Pair<A, B>> l) {\n"
				+ "\t\tList<A> l1 = (List<A>)new ArrayList<A>();\n"
				+ "\t\tList<B> l2 = (List<B>)new ArrayList<B>();\n"
				+ "\t\tfor (Pair<A, B> element : l) {\n\t\t\tl1.add(element.a());\n\t\t\tl2.add(element.b());\n\t\t}\n"
				+ "\t\treturn new Pair<List<A>, List<B>>(l1, l2);\n\t}\n\n";
		List<? extends Element> le = element.getEnclosedElements();
		for (Element e: le) {
			String methodName = e.getSimpleName().toString();
			String[] args = {methodName, typeArgs};
			classContent += e.asType().accept(new CombinatorTypeVisitor(), args);
		}
		classContent += "}";
		return classContent;
	}

}
