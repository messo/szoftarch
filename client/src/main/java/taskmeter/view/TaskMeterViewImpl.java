package taskmeter.view;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import taskmeter.view.gui.ApplicationPanel;
import taskmeter.view.gui.LoginPanel;
import taskmeter.view.gui.ProgressPanel;
import taskmeter.ws.Project;

public class TaskMeterViewImpl extends JFrame implements TaskMeterView {

	private JPanel contentPane;
	private final LoginPanel loginPanel;
	private final ProgressPanel progressPanel;
	private final ApplicationPanel applicationPanel;

	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu, aboutMenu;
	private JMenuItem authorsMenuItem, syncMenuItem, hideMenuItem,
			exitMenuItem;
	private final JMenuItem logoutMenuItem = new JMenuItem("Log out");

	private String syncingMenuItemText = "Sync";

	private TrayIcon trayIcon;
	private SystemTray systemTray;
	final PopupMenu systemTrayPopupMenu = new PopupMenu();
	final MenuItem systemTrayExitMenuItem = new MenuItem("Exit");
	final MenuItem systemTrayReopenMenuItem = new MenuItem("Open");

	private final ActionListener toSystemTrayActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				systemTray.add(trayIcon);
				setVisible(false);
				System.out.println("added to SystemTray");
			} catch (AWTException ex) {
				showErrorMessage("Something went wrong, we can't add window to systemtray");
			}
		}
	};

	private final WindowStateListener toSystemTrayWindowStateListener = new WindowStateListener() {
		public void windowStateChanged(WindowEvent e) {
			if (e.getNewState() == ICONIFIED) {
				try {
					systemTray.add(trayIcon);
					setVisible(false);
					System.out.println("added to SystemTray");
				} catch (AWTException ex) {
					showErrorMessage("Something went wrong, we can't add window to systemtray");
				}
			}
			if (e.getNewState() == 7) {
				try {
					systemTray.add(trayIcon);
					setVisible(false);
					System.out.println("added to SystemTray");
				} catch (AWTException ex) {
					showErrorMessage("Something went wrong, we can't add window to systemtray");
				}
			}
			if (e.getNewState() == MAXIMIZED_BOTH) {
				systemTray.remove(trayIcon);
				setVisible(true);
				System.out.println("Tray icon removed");
			}
			if (e.getNewState() == NORMAL) {
				systemTray.remove(trayIcon);
				setVisible(true);
				System.out.println("Tray icon removed");
			}
		}
	};

	/**
	 * Create the frame.
	 */
	public TaskMeterViewImpl() {
		System.out.println(getClass().getName());

		loginPanel = new LoginPanel();
		progressPanel = new ProgressPanel();
		applicationPanel = new ApplicationPanel();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		createMenu();

		if (SystemTray.isSupported()) {
			systemTray = SystemTray.getSystemTray();

			final Image systemTrayImage = Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/icon/timer26.png"));

			systemTrayPopupMenu.add(systemTrayExitMenuItem);
			systemTrayReopenMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(true);
					setExtendedState(JFrame.NORMAL);
				}
			});
			systemTrayPopupMenu.add(systemTrayReopenMenuItem);
			trayIcon = new TrayIcon(systemTrayImage, "TaskMeter",
					systemTrayPopupMenu);
			trayIcon.setImageAutoSize(true);

			addWindowStateListener(toSystemTrayWindowStateListener);
		}

		try {
			setIconImage(Toolkit.getDefaultToolkit().getImage(
					LoginPanel.class.getResource("/icon/timer512.png")));
		} catch (Exception e) {
			e.getStackTrace();
		}

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.setSize(dim.getWidth() / 2, dim.getHeight() / 2);

		setLocation(dim.width - 300, dim.height - 300);
		setSize(600, 600);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		showLogin();
	}

	private void createMenu() {
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_1);

		menuBar.add(fileMenu);

		syncMenuItem = new JMenuItem(syncingMenuItemText);
		syncMenuItem.setMnemonic(KeyEvent.VK_B);

		fileMenu.add(syncMenuItem);

		fileMenu.addSeparator();

		fileMenu.add(logoutMenuItem);

		hideMenuItem = new JMenuItem("Hide");
		hideMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				ActionEvent.ALT_MASK));
		hideMenuItem.addActionListener(toSystemTrayActionListener);
		fileMenu.add(hideMenuItem);

		exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		// Build second menu in the menu bar.
		aboutMenu = new JMenu("About");
		authorsMenuItem = new JMenuItem("Authors");
		authorsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Készítette: Döbrei Gábor és Kriván Bálint\n2013 november",
								"About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		aboutMenu.add(authorsMenuItem);
		menuBar.add(aboutMenu);
	}

	private LoginPanel getLoginPanel() {
		return loginPanel;
	}

	private ProgressPanel getProgressPanel() {
		return progressPanel;
	}

	private ApplicationPanel getApplicationPanel() {
		return applicationPanel;
	}

	@Override
	public void showLogin() {
		contentPane.removeAll();
		setResizable(false);
		getRootPane().setDefaultButton(getLoginPanel().getLoginButton());

		contentPane.add(getLoginPanel());
		setSize(getLoginPanel().getPreferredSize());
		repaint();
	}

	@Override
	public void showProgress() {
		hideMenu();
		contentPane.removeAll();

		contentPane.add(getProgressPanel());
		setSize(getProgressPanel().getPreferredSize());
		repaint();
	}

	@Override
	public void showApplication() {
		setResizable(true);
		contentPane.removeAll();

		showMenu();

		contentPane.add(applicationPanel);
		setSize(applicationPanel.getPreferredSize());
		repaint();
	}

	@Override
	public String getUserName() {
		return getLoginPanel().getUserName();
	}

	@Override
	public char[] getPassword() {
		return getLoginPanel().getPassword();
	}

	@Override
	public void addLoginActionListener(ActionListener al) {
		getLoginPanel().setLoginButtonActionListener(al);
	}

	@Override
	public void addLogoutActionListener(ActionListener al) {
		logoutMenuItem.addActionListener(al);
	}

	@Override
	public void addSyncronizeActionListener(ActionListener al) {
		getApplicationPanel().setSyncronizeActionListener(al);
		syncMenuItem.addActionListener(al);
	}

	@Override
	public void addStartStopActionListener(ActionListener al) {
		getApplicationPanel().setStartStopButtonActionListener(al);
	}

	@Override
	public void addExitActionListener(ActionListener al) {
		systemTrayExitMenuItem.addActionListener(al);
		exitMenuItem.addActionListener(al);
	}

	@Override
	public void addCloseSafelyListener(WindowAdapter wa) {
		addWindowListener(wa);
	}

	@Override
	public void showErrorMessage(String msg) {
		getLoginPanel().showErrorMessage(msg);
	}

	@Override
	public int getSelectedProjectID() {
		return getApplicationPanel().getSelectedProjectID();
	}

	public void changeToStop() {
		getApplicationPanel().changeToStop();
	}

	public void changeToStart() {
		getApplicationPanel().changeToStart();
	}

	public void setProjectList(List<Project> plist) {
		getApplicationPanel().setProjects(plist);
		validate();
	}

	public void setOtherOptionsUnavailable() {
		getApplicationPanel().setRadioButtonsUnavailable();
	}

	public void setAllOptionsAvailable() {
		getApplicationPanel().setAllRadioButtonsAvailable();
	}

	public void hideMenu() {
		setJMenuBar(null);
	}

	public void showMenu() {
		setJMenuBar(menuBar);
	}

	public void unableSync() {
		syncMenuItem.setEnabled(false);
		getApplicationPanel().unableSync();
	}

	public void enableSync() {
		syncMenuItem.setEnabled(true);
		getApplicationPanel().enableSync();
	}

	public void clearTextFields() {
		getLoginPanel().clearTextFields();
	}

	public void clearPasswordField() {
		getLoginPanel().clearPasswordField();
	}
}
