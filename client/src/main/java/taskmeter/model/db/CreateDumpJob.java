package taskmeter.model.db;

import java.util.LinkedList;

import taskmeter.ws.WorkingSlot;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteJob;
import com.almworks.sqlite4java.SQLiteStatement;

public class CreateDumpJob extends SQLiteJob<LinkedList<WorkingSlot>> {

	@Override
	protected LinkedList<WorkingSlot> job(SQLiteConnection conn)
			throws Throwable {
		String statement = "SELECT * FROM workingslot";

		SQLiteStatement stmt = conn.prepare(statement);

		LinkedList<WorkingSlot> dump = new LinkedList<WorkingSlot>();

		while (stmt.step()) {
			WorkingSlot ws = new WorkingSlot();

			ws.setId(stmt.columnInt(0));
			ws.setProjectID(stmt.columnInt(1));
			ws.setUserID(stmt.columnInt(2));
			ws.setStartTimestamp(stmt.columnLong(3));
			ws.setStopTimestamp(stmt.columnLong(4));
			dump.add(ws);
		}

		return dump;
	}
}
