package taskmeter.view.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import taskmeter.ws.Project;

public class ApplicationPanel extends JPanel {

	private JButton startStopButton;
	private JButton syncButton;
	private ButtonGroup group = new ButtonGroup();
	private JPanel buttonPanel = new JPanel();

	private JPanel centerPanel = new JPanel();
	private LinkedList<JRadioButton> radioButtonList = new LinkedList<JRadioButton>();

	/**
	 * Create the panel.
	 */
	public ApplicationPanel() {
		setLayout(new BorderLayout(0, 0));

		startStopButton = new JButton("Start");
		buttonPanel.add(startStopButton);

		syncButton = new JButton("Sync");
		buttonPanel.add(syncButton);

		buttonPanel.setSize(400, 100);

		add(buttonPanel, BorderLayout.SOUTH);

		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 1, 0, 0));

	}

	public void setSyncronizeActionListener(ActionListener al) {
		syncButton.addActionListener(al);
	}

	public void setStartStopButtonActionListener(ActionListener al) {
		startStopButton.addActionListener(al);
	}

	public int getSelectedProjectID() {
		try {
			return Integer.parseInt(group.getSelection().getActionCommand());
		} catch (Exception e) {
			return -1000;
		}
	}

	public void changeToStop() {
		startStopButton.setText("Stop");
	}

	public void changeToStart() {
		startStopButton.setText("Start");
	}

	public void setProjects(List<Project> plist) {

		centerPanel.removeAll();
		
		for (Project p : plist) {
			JRadioButton radioButton = new JRadioButton(p.getProjectName());
			radioButton.setActionCommand(String.valueOf(p.getProjectID()));

			radioButton.setBorderPainted(true);

			radioButtonList.add(radioButton);
			group.add(radioButton);
			centerPanel.add(radioButton);
		}

		setSize(400, 100 + plist.size() * 30);
		setPreferredSize(getSize());

	}

	public void setRadioButtonsUnavailable() {
		for (JRadioButton rb : radioButtonList) {
			if (!rb.getActionCommand().equals(
					group.getSelection().getActionCommand())) {
				rb.setEnabled(false);
			}
		}
	}

	public void setAllRadioButtonsAvailable() {
		for (JRadioButton rb : radioButtonList) {
			rb.setEnabled(true);
		}
	}

	public void unableSync() {
		syncButton.setEnabled(false);
	}
	
	public void enableSync() {
		syncButton.setEnabled(true);
	}
}
