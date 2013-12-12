package taskmeter.model.db;

import java.util.ArrayList;
import java.util.List;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteJob;
import com.almworks.sqlite4java.SQLiteStatement;

public class CleanDBJob extends SQLiteJob<Object> {

	private ArrayList<Integer> savedIds;

	public CleanDBJob(List<Integer> savedIds) {
		this.savedIds = (ArrayList<Integer>) savedIds;
	}

	@Override
	protected Object job(SQLiteConnection conn) throws Throwable {
		try {

			// először is az ACK-s recordokat dobjuk ki
			for (Integer id : savedIds) {
				String statement = "DELETE FROM workingslot WHERE ID = " + id;
				conn.exec(statement);
			}

			// utána a túl rövideket is
			String statement = "DELETE FROM workingslot WHERE (StopTimestamp - StartTimestamp) < 5000";
			conn.exec(statement);

			// utána, ha üres a DB, akkor kinullázzuk az ID-t
			SQLiteStatement stmt = conn
					.prepare("SELECT COUNT(*) FROM workingslot");
			if (!stmt.hasRow()) {
				conn.exec("UPDATE sqlite_sequence SET seq = 0");
			}

		} catch (SQLiteException e) {
			e.printStackTrace();
		}

		return null;
	}
}
