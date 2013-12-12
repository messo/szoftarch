package taskmeter;

import java.util.Locale;

import taskmeter.model.TaskMeterModel;
import taskmeter.view.TaskMeterViewImpl;

public class TaskMeterClientApplication {

	public static void main(String[] args) {
		System.out.println("Main");
		TaskMeterModel taskMeterModel = new TaskMeterModel();

		TaskMeterViewImpl taskMeterView = new TaskMeterViewImpl();

		@SuppressWarnings("unused")
		TaskMeterController taskMeterController = new TaskMeterController(
				taskMeterModel, taskMeterView);

		taskMeterView.setVisible(true);

		Locale.setDefault(Locale.US);

	}

}