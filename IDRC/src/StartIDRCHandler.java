import idrc.Activator;
import idrc.util.IDRCUtil;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;


public class StartIDRCHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("CommandHandle!");
		if(Activator.getProject()==null){
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		ISelection selection=window.getSelectionService().getSelection();
		IResource resource=extractSelection(selection);
				IProject project = null;
				IJavaProject javaProject = null;
					project = resource.getProject();
					javaProject = JavaCore.create(project);
				if (project != null && javaProject != null) {
					
					Activator.setProject(javaProject);
				}}
		else{
			IDRCUtil.print("Currently"+Activator.getProject().toString()+"project is set. Please clear it first");	
		}
		IDRCUtil.print(Activator.getProject().toString());
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//will return currently selected Iresource
	 IResource extractSelection(ISelection sel) {
	      if (!(sel instanceof IStructuredSelection))
	         return null;
	      IStructuredSelection ss = (IStructuredSelection) sel;
	      Object element = ss.getFirstElement();
	      if (element instanceof IResource)
	         return (IResource) element;
	      if (!(element instanceof IAdaptable))
	         return null;
	      IAdaptable adaptable = (IAdaptable)element;
	      Object adapter = adaptable.getAdapter(IResource.class);
	      return (IResource) adapter;
	   }

}
