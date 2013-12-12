package taskmeter.model.db;

import java.io.File;

import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteQueue;

public class LocalDatabase {

	private File databaseFile;
	private static final String DB_PATH = "db/tasks";
	private SQLiteQueue queue;
	private static LocalDatabase instance = null;

	public static synchronized LocalDatabase get() {
		if (instance == null) {
			try {
				instance = new LocalDatabase();
				instance.queue().execute(new InitJob());
			} catch (SQLiteException e) {
			}
		}
		return instance;
	}

	private LocalDatabase() throws SQLiteException {
		System.out.println(getClass().getName());

		databaseFile = new File(DB_PATH);
		// conn = new SQLiteConnection();
		// conn.open(true);
		queue = new SQLiteQueue(databaseFile);
		queue.start();
	}

	public static boolean deleteDBFile() {
		File file = new File(DB_PATH);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	public SQLiteQueue queue() {
		return queue;
	}

	public void dispose() throws InterruptedException {
		if (queue != null) {
			queue.stop(true).join();
		}
	}

}