package taskmeter.model.db;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteJob;

public class InitJob extends SQLiteJob<Object> {

	public InitJob() {
	}

	@Override
	protected Object job(SQLiteConnection conn) throws Throwable {

		/**
		 * Nem lehet törölni, mert ha az előző futás végén nem tudott
		 * szinkronizálni, akkor elvesznek az adatok...
		 */
		// conn.exec("DROP TABLE IF EXISTS workingslot");

		String statement = "CREATE TABLE IF NOT EXISTS workingslot ("
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "ProjectID INTEGER, " + "UserID INTEGER, "
				+ "StartTimestamp INTEGER NOT NULL, "
				+ "StopTimestamp INTEGER NOT NULL)";
		conn.exec(statement);

		return null;
	}

}
