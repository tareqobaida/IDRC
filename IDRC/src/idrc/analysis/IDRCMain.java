package idrc.analysis;

import idrc.Activator;
import idrc.ast.ASTBuilder;
import idrc.ast.VarDecVisitor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class IDRCMain {
IJavaProject project;
IPackageFragment[] packages=null;
Map <String,CompilationUnit> cunits=new HashMap<String, CompilationUnit>();
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
	        	cunits.put(unit.getElementName(),ASTBuilder.getASTBuilder().parse(unit)); //store in the map for later use
	          }
	      }
	    }
}


public void printAST(){
	for(Map.Entry<String,CompilationUnit> entry:cunits.entrySet()){
		CompilationUnit cu=entry.getValue();
		VarDecVisitor vstr = new VarDecVisitor();
		cu.accept(vstr);
		System.out.println("source="+entry.getKey());
		System.out.println(cu.types().toString());
	}
}
}
