package compilation;

import idrc.util.IDRCUtil;

import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.compiler.CompilationParticipant;
import org.eclipse.jdt.core.compiler.ReconcileContext;

public class IDRCCompilationParticipant extends CompilationParticipant {

	public IDRCCompilationParticipant() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void buildFinished(IJavaProject project) {
		// do nothing by default
		IDRCUtil.print("build");
		System.out.println("build finished");
	}
	public void reconcile(ReconcileContext context) {
		// do nothing by default
		System.out.println("reconcile");
		IDRCUtil.print("recon");
			IJavaElementDelta delta = context.getDelta();
			if(delta.getKind()== IJavaElementDelta.CHANGED && 
		 (delta.getFlags()&IJavaElementDelta.F_CONTENT)!=0){
			 System.out.println("Content changed!");}
}
}
