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
public class AlgebraProcessor extends AbstractProcessor{

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
			String typeArguments = "<" + typeArgs + ">";
			String[] lTypeArgs = toList(typeArgs);
			algName = element.getSimpleName().toString();
			
			
			// Create generic classes "G_AlgName_***".
			folder = "generic";
			for (String s: lTypeArgs){
				classContent = createGenericClass(folder, s, typeArguments, element);
				try{
					jfo = filer.createSourceFile(folder + "/" + "G_" + element.getSimpleName() + "_"+ s, element);
					jfo.openWriter().append(classContent).close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
			
			// Create transform classes "AlgNameTransform".
			folder = "transform";
			classContent = createTransformClass(folder, element, lTypeArgs, typeArguments);
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
	
	String createGenericClass(String folder, String className, String typeArguments, Element element){
		return "package " + folder + ";\n\n"
				+ "import " + getPackage(element) + "." + element.getSimpleName() + ";\n\n"
				+ "public interface G_" + element.getSimpleName() + "_" + className + " {\n" +
				"\t" + typeArguments + " " + className + " accept(" + element.getSimpleName() + typeArguments + " alg);\n}";
	}
	
	private String getPackage(Element element) {
		return ((PackageElement)element.getEnclosingElement()).getQualifiedName().toString();
	}
	
	String createTransformClass(String folder, Element element, String[] lTypeArgs, String typeArguments){
		List<? extends Element> le = element.getEnclosedElements();
		String className = element.getSimpleName() + "Transform";
		String classContent = "package " + folder + ";\n\n";
		classContent += "import " + getPackage(element) + "." + element.getSimpleName() + ";\n";
		classContent += "import generic.*;\n\n";
		classContent += "public interface " + className + " extends " + element.getSimpleName() + "<";
		for (int i = 0; i < lTypeArgs.length; ++i){
			classContent += "G_" + element.getSimpleName() + "_" + lTypeArgs[i];
			if (i < lTypeArgs.length-1) classContent += ", ";
		}
		classContent += "> {\n";
				
		for (Element e: le){
			String[] args = {e.getSimpleName().toString(), typeArguments, element.getSimpleName().toString()};
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
			if (i < lTypeArgs.length-1) classContent += ",";
		}
		classContent += "> {\n" + 
				"\tprivate Monoid<R> m;\n\tpublic Monoid<R> m() { return m; }\n\tpublic " + algName + "Query(Monoid<R> m) {\n\t\tthis.m = m;\n\t}\n";
		List<? extends Element> le = element.getEnclosedElements();
		for (Element e: le){
			String methodName = e.getSimpleName().toString();
			String[] args = {methodName, typeArgs};
			classContent += e.asType().accept(new QueryExecutableTypeVisitor(), args);
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
				+ "\tprivate " + algName + "<" + alg1 + "> alg1;\n\tprivate " + algName + "<" + alg2 + "> alg2;\n\n"
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
