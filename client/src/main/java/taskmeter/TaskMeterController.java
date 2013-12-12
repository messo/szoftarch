package taskmeter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import taskmeter.model.TaskMeterModel;
import taskmeter.model.User;
import taskmeter.view.TaskMeterView;

import com.google.common.base.Stopwatch;

public class TaskMeterController {

	private TaskMeterModel model;
	private TaskMeterView view;
	private boolean started = false;
	private int selectedProjectID = -1000;

	private final Stopwatch stopper = Stopwatch.createUnstarted();

	public TaskMeterController(TaskMeterModel theModel, TaskMeterView theView) {
		System.out.println(getClass().getName());
		model = theModel;
		view = theView;

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				if (started) {

					model.insertOrUpdateRecordToProject(selectedProjectID);

					String pn = model.getProjectNameByID(selectedProjectID);

					final DecimalFormat df = new DecimalFormat("00");

					String title = MessageFormat.format("{0} - {1}:{2}:{3}",
							pn, df.format(stopper.elapsed(TimeUnit.HOURS)),
							df.format(stopper.elapsed(TimeUnit.MINUTES)),
							df.format(stopper.elapsed(TimeUnit.SECONDS)));
					view.setTitle(title);

				}
			}
		}, 0, 1000);

		view.addLoginActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out
						.println("TaskMeterController.TaskMeterController(...).new ActionListener() {...}.actionPerformed()");

				view.showProgress();

				Thread checkUserNameAndPasswordThread = new Thread(
						new Runnable() {
							@Override
							public void run() {
								try {
									String username = view.getUserName();
									char[] password = view.getPassword();

									switch (model.login(username, password)) {
									case -1:
										view.showErrorMessage("Nem érhető el a szerver!\nSajnos nem fog tudni bejelentkezni!");
										view.showLogin();
										break;

									case -2:
										view.clearPasswordField();
										view.showErrorMessage("Rossz jelszó vagy felhasználónév!");
										view.showLogin();
										break;

									default:
										User user = model.getUser();

										view.setProjectList(user
												.getProjectList());
										view.clearTextFields();
										view.showApplication();
										break;
									}
									Arrays.fill(password, '0');

								} catch (Exception e2) {
									e2.printStackTrace();
								}
							}

						});
				checkUserNameAndPasswordThread.start();
			}
		});

		view.addLogoutActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				view.showProgress();

				Thread logoutThread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {

							model.logout();
							view.hideMenu();
							view.setTitle(null);
							view.showLogin();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				});
				logoutThread.start();

			}
		});

		view.addSyncronizeActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Thread uploadUsersDataThread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {

							/**
							 * felölti az adatokat és frissíti a projekt listát
							 */
							if (model.syncronize() != 0) {
								view.showErrorMessage("Nem sikerült a szinkronizáció!\nValószínűleg nem ellérhető a szerver. Az adatok nem vesztek el!");
							}

							/**
							 * Az esetleg új projekt listát írjuk ki.
							 */
							view.setProjectList(model.getUser()
									.getProjectList());

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				uploadUsersDataThread.start();

			}
		});

		view.addStartStopActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (view.getSelectedProjectID() != -1000) {
					try {
						if (started) {
							started = false;
							view.changeToStart();
							view.setAllOptionsAvailable();
							view.setTitle(null);
							stopper.stop();
							view.enableSync();

						} else {
							stopper.reset();
							stopper.start();
							started = true;
							view.changeToStop();
							view.setOtherOptionsUnavailable();
							selectedProjectID = view.getSelectedProjectID();

							view.unableSync();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

		});

		view.addExitActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				view.showProgress();

				Thread exitThread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {

							Thread.sleep(1000);

							model.logout();
							System.exit(0);

						} catch (InterruptedException e1) {
							e1.printStackTrace();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}

				});
				exitThread.start();

			}
		});

		view.addCloseSafelyListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				view.showProgress();

				Thread exitThread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {

							Thread.sleep(1000);

							model.logout();
							System.exit(0);

						} catch (InterruptedException e1) {
							e1.printStackTrace();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}

				});
				exitThread.start();
			}
		});

	}
}
