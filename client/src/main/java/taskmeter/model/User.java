package taskmeter.model;

import java.util.ArrayList;
import java.util.List;

import taskmeter.ws.Project;

public class User {
	private int userID = 1;
	private String userName = "TaskMeter Default Project";
	private ArrayList<Project> projectList;

	public User() {
		System.out.println(getClass().getName());
		projectList = new ArrayList<Project>();

	}

	public User(int iD, String userName) {
		System.out.println(getClass().getName());
		setID(iD);
		setUserName(userName);
		projectList = new ArrayList<Project>();
	}

	public void workingOn(Project project) {
		getProjectList().add(project);
	}

	public boolean isWorkingOn(Project project) {
		return getProjectList().contains(project);
	}

	public int getID() {
		return userID;
	}

	public void setID(int iD) {
		userID = iD;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = (ArrayList<Project>) projectList;
	}

}
