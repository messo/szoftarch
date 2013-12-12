package taskmeter.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import taskmeter.model.db.CleanDBJob;
import taskmeter.model.db.CreateDumpJob;
import taskmeter.model.db.InitJob;
import taskmeter.model.db.InsertOrUpdateRecordToProjectJob;
import taskmeter.model.db.LocalDatabase;
import taskmeter.ws.Project;
import taskmeter.ws.WorkingSlot;

public class TaskMeterModel {

	private User user;
	private LocalDatabase localDB;
	private final RemoteTaskMeterServerProxy server = new RemoteTaskMeterServerProxy();

	public TaskMeterModel() {
		System.out.println(getClass().getName());
		user = new User();
		localDB = LocalDatabase.get();

		localDB.queue().execute(new InitJob());

	}

	/*** Segédfüggvények ***/

	public String getProjectNameByID(int projectID) {
		for (Project p : user.getProjectList()) {
			if (p.getProjectID() == projectID) {
				return p.getProjectName();
			}
		}
		return null;
	}

	public User getUser() {
		return user;
	}

	/*** Server függvények ***/

	public int login(String un, char[] pw) {

		try {
			int userId = server.checkUsernameAndPassword(un, pw);
			if (userId != -1) {
				user.setID(userId);
				user.setUserName(un);
				user.setProjectList(server.getUserCurrentProjects(user));

				System.err.println(user.getProjectList());

				Arrays.fill(pw, '0');
				return 0;
			} else {
				return -2;
			}
		} catch (Exception e) {
			return -1;
		}

	}

	public void logout() {

		syncronize();
		// localDB.queue().execute(new ResetDBJob());

	}

	public int syncronize() {
		/**
		 * Értelmes adatok feltöltése.
		 */
		try {
			LinkedList<WorkingSlot> toServer = new LinkedList<WorkingSlot>();

			LinkedList<WorkingSlot> fromDB = localDB.queue()
					.execute(new CreateDumpJob()).get();
			for (WorkingSlot workingSlot : fromDB) {

				// Csak az 5 ms-nél hosszabbakat vesszük figyelembe
				if ((workingSlot.getStopTimestamp() - workingSlot
						.getStartTimestamp()) > 4999) {
					toServer.add(workingSlot);
				}
			}

			System.out.println(toServer);

			List<Integer> ack;
			ack = server.syncUserData(toServer);
			System.err.println("kidobandó elemek:" + ack);
			if (ack != null) {
				localDB.queue().execute(new CleanDBJob(ack));
			} else {
				return -1;
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		} catch (ExecutionException e) {
			e.printStackTrace();
			return -1;
		}

		/**
		 * Projekt lista frissítése
		 */
		user.setProjectList(server.getUserCurrentProjects(user));
		return 0;
	}

	/*** Local DB részek ***/
	public void insertOrUpdateRecordToProject(int projectID) {
		localDB.queue().execute(
				new InsertOrUpdateRecordToProjectJob(projectID, getUser()
						.getID()));
	}

}