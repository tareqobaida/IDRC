package idrc.analysis;

import java.util.HashMap;
import java.util.Map;

import idrc.Activator;
import idrc.ASTVisitor.ASTNodeAnnotationVisitor;
import idrc.ast.ASTBuilder;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class IDRCMain {
IJavaProject project;
IPackageFragment[] packages=null;
Map <ICompilationUnit,CompilationUnit> cunits=new HashMap<ICompilationUnit, CompilationUnit>();
public IDRCMain(){
	this.project=Activator.getProject();
}


public void getAST() throws JavaModelException{
	 this.packages=project.getPackageFragments();

	 for (IPackageFragment mypackage : packages) {
//	       We will only look at the package from the source folder
	      if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
	        System.out.println("Package " + mypackage.getElementName());
	        for (ICompilationUnit unit : mypackage.getCompilationUnits()) { //IcompilationUnit is any source file
	        	cunits.put(unit,ASTBuilder.getASTBuilder().parse(unit)); //store in the map for later use
	          }
	      }
	    }
}


public void printAST(){
	for(Map.Entry<ICompilationUnit,CompilationUnit> entry:cunits.entrySet()){
		CompilationUnit cu=entry.getValue();
		ICompilationUnit icu=entry.getKey();
		ASTNodeAnnotationVisitor annvisitor=new ASTNodeAnnotationVisitor(cu, icu);
		annvisitor.process();
		System.out.println("source="+entry.getKey());
		System.out.println(cu.types().toString());
	}
}
}
