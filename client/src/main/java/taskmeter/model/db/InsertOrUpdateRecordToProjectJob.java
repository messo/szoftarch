package taskmeter.model.db;

import java.text.MessageFormat;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteJob;
import com.almworks.sqlite4java.SQLiteStatement;

public class InsertOrUpdateRecordToProjectJob extends SQLiteJob<Object> {

	private final int projectID;
	private final int userID;

	public InsertOrUpdateRecordToProjectJob(int projectID, int userID) {
		this.projectID = projectID;
		this.userID = userID;
	}

	@Override
	protected Object job(SQLiteConnection conn) throws Throwable {

		String statement = MessageFormat
				.format("SELECT ID, StopTimestamp FROM workingslot WHERE ProjectID = {0} ORDER BY StopTimestamp DESC",
						String.valueOf(projectID));

		SQLiteStatement stmt = conn.prepare(statement);
		stmt.step();

		// Már mértünk erre a projektre, de régen; vagy még nem is mértünk rá
		if (!stmt.hasRow()) {
			statement = MessageFormat
					.format("INSERT INTO workingslot (ProjectID, UserID, StartTimestamp, StopTimestamp) VALUES ({0}, {1}, {2}, {2});",
							String.valueOf(projectID), String.valueOf(userID),
							String.valueOf(System.currentTimeMillis()));

		} else {
			if (System.currentTimeMillis() - stmt.columnLong(1) > 6000) {
				System.err.println("van már sor, de régi");
				statement = MessageFormat
						.format("INSERT INTO workingslot (ProjectID, UserID, StartTimestamp, StopTimestamp) VALUES ({0}, {1}, {2}, {2});",
								String.valueOf(projectID),
								String.valueOf(userID),
								String.valueOf(System.currentTimeMillis()));
			} else {

				statement = MessageFormat
						.format("UPDATE workingslot SET StopTimestamp = {0} WHERE ID = {1}",
								String.valueOf(System.currentTimeMillis()),
								String.valueOf(stmt.columnInt(0)));
			}
		}

		System.out.println(statement);
		try {
			conn.exec(statement);
		} catch (SQLiteException e) {
			e.printStackTrace();
		}

		return null;
	}
}
