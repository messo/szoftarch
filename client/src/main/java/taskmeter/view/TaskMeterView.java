package taskmeter.view;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.List;

import taskmeter.ws.Project;

public interface TaskMeterView {

	public void addLoginActionListener(ActionListener al);
	
	public void addLogoutActionListener(ActionListener al);

	public void addSyncronizeActionListener(ActionListener al);

	public void addStartStopActionListener(ActionListener al);
	
	public void addExitActionListener(ActionListener al);
	
	public void addCloseSafelyListener(WindowAdapter wa);

	public void showLogin();

	void showProgress();

	public void showApplication();
	
	public void showErrorMessage(String msg);

	public String getUserName();

	public char[] getPassword();
	
	public int getSelectedProjectID();

	public void setProjectList(List<Project> projectList);

	public void clearTextFields();

	public void clearPasswordField();

	public void setTitle(String title);

	public void hideMenu();

	public void changeToStart();

	public void setAllOptionsAvailable();

	public void enableSync();

	public void changeToStop();

	public void setOtherOptionsUnavailable();

	public void unableSync();
}
