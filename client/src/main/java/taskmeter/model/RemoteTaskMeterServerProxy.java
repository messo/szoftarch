package taskmeter.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import taskmeter.ws.Project;
import taskmeter.ws.TaskmeterService;
import taskmeter.ws.TaskmeterWS;
import taskmeter.ws.WorkingSlot;

public class RemoteTaskMeterServerProxy {

	private TaskmeterService taskmeterService = new TaskmeterWS()
			.getTaskmeterServicePort();

	public RemoteTaskMeterServerProxy(){
		
	}
	
	public int checkUsernameAndPassword(String un, char[] pw) {
		return taskmeterService.login(un, getMD5(new String(pw)));
	}

	public List<Project> getUserCurrentProjects(User user) {
		return taskmeterService.listProjects(user.getID());
	}

	public List<Integer> syncUserData(LinkedList<WorkingSlot> sqlDump) {

		try {
			return taskmeterService.synchronize(sqlDump);
		} catch (Exception e) {
			return null;
		}
	}

	private String getMD5(String password) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");

			byte[] digest = md5.digest(password.getBytes("UTF-8"));

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digest.length; ++i) {
				sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
}
