package taskmeter.view.gui;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ProgressPanel extends ImagePanel {

	public ProgressPanel() {
		super(Toolkit.getDefaultToolkit().getImage(
				ProgressPanel.class.getResource("/background/bg2.jpg")));
		ImageIcon img = new ImageIcon(
				ProgressPanel.class.getResource("/anim/loading300x400.gif"));

		setSize(600, 600);
		setPreferredSize(getSize());

		final int width = img.getIconWidth();
		final int height = img.getIconWidth();

		JLabel label = new JLabel(img);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		// 600x600 ablakba tesszük középre a 300x300-as gif-et
		label.setBounds(300 - width / 2, 300 - height / 2, width, height);
		add(label);
	}
}
