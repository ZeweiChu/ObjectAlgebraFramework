package com.zewei.annotation.processor;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.IntersectionType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.UnionType;
import javax.lang.model.type.WildcardType;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes(value={"com.zewei.annotation.processor.Query"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class QueryProcessor extends AbstractProcessor{

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
		String[] realTypeArguments = null;
		String algName;
		JavaFileObject jfo = null;
		for (Element element: env.getElementsAnnotatedWith(Query.class)){			
			algName = element.getSimpleName().toString();
			TypeMirror tm = element.asType();
			String typeArgs = tm.accept(new DeclaredTypeVisitor(), element);
			String[] lTypeArgs = toList(typeArgs);

			folder = "query";
			try {
				Query queryAnnotation = element.getAnnotation(Query.class);
				realTypeArguments = queryAnnotation.typeArguments();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			}

			for (String realTypeArgument: realTypeArguments){
				classContent = "";
				classContent +=  "package " + folder + ";\n"
						+ "import library.Monoid;\n"
						+ "import " + element.getEnclosingElement().getSimpleName() + "." + element.getSimpleName() + ";\n" 
						+ "public interface " + realTypeArgument + "Query" + algName + " extends " + algName + "<";
				for (int i = 0; i < lTypeArgs.length; i++){
					classContent += realTypeArgument;
					if (i < lTypeArgs.length-1) classContent += ",";
				}
				classContent += ">, Monoid<" + realTypeArgument + ">{\n";
				
				List<? extends Element> le = element.getEnclosedElements();
				for (Element e: le){
					String methodName = e.getSimpleName().toString();
					String[] args = {methodName, typeArgs, realTypeArgument};
					classContent += e.asType().accept(new ExecutableTypeVisitor(), args);
				}
				classContent += "}";
				
				jfo = null;
				try{
					jfo = filer.createSourceFile(folder + "/" + realTypeArgument + "Query" + algName, element);
					jfo.openWriter().append(classContent).close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
		return true;
		
	}
	
	private String[] toList(String message) {
		return message.split(",");
	}

	String toAnchorList(String message){
		int len = message.length();
		String res = "<" + message.substring(1, len-1)+">";
		return res;
	}
	
	@Override
	public SourceVersion getSupportedSourceVersion(){
		return SourceVersion.latestSupported();
	}


	public class DeclaredTypeVisitor implements TypeVisitor<String, Element>{

		@Override
		public String visitDeclared(DeclaredType t, Element p) {
			String res = t.getTypeArguments().toString().replace(" ", "");
			int len = res.length();
			res = res.substring(1, len-1);
			return res;
		}

		@Override
		public String visit(TypeMirror t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visit(TypeMirror t) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitPrimitive(PrimitiveType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitNull(NullType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitArray(ArrayType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitError(ErrorType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitTypeVariable(TypeVariable t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitWildcard(WildcardType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitNoType(NoType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitUnknown(TypeMirror t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitUnion(UnionType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public String visitIntersection(IntersectionType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String visitExecutable(ExecutableType t, Element p) {
			// TODO Auto-generated method stub
			return null;
		}


	}
	
	
	
	
	public class ExecutableTypeVisitor implements TypeVisitor<String, String[]>{
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
			String realTypeArgument = p[2];
			
			List<? extends TypeMirror> lp = t.getParameterTypes();
			String returnType = t.getReturnType().toString();
			
			String res = " @Override\n" 
						+ " default public ";
			
			
			int flag = arrayContains(lTypeArgs, returnType);
			if (flag != -1 && returnType.contains("java.util.List"))	res += "java.util.List<" + realTypeArgument + "> ";
			else if (flag != -1) res +=  realTypeArgument + " ";
			else res += returnType + " ";
			
			res += methodName + "(";
			
			
			for (int i = 0; i < lp.size(); ++i){
				flag = arrayContains(lTypeArgs, lp.get(i).toString());
				if (flag != -1 && lp.get(i).toString().contains("java.util.List"))	res += "java.util.List<" + realTypeArgument + "> p" + i;
				else if (flag != -1) res +=  realTypeArgument + " p" + i;
				else res += lp.get(i).toString() + " p" + i;
				if (i < lp.size()-1) res += ",";
			}
			
			res += "){\n";
			res += "  " + realTypeArgument + " res = empty();\n";
			for (int i = 0; i < lp.size(); ++i){
				flag = arrayContains(lTypeArgs, lp.get(i).toString());
				if (flag != -1 && lp.get(i).toString().contains("java.util.List"))	res += "  res = join(res, fold(p" + i + "));\n";
				else if (flag != -1)	res += "  res = join(res, p" + i + ");\n";
			}
			res += "  return res;\n";
			res += " }\n";
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
}
