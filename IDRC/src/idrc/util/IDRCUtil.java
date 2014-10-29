package idrc.util;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class IDRCUtil {
	private static IWorkbenchWindow window ;
	
	
	public static void print(String x) {
		window=PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		MessageDialog.openInformation(
			window.getShell(),
			"IDRC",
			x);
		System.out.println(x);
	}

}
