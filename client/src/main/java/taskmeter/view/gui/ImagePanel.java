package taskmeter.view.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private Image img;
	private int offsetX = 0, offsetY = 0;
	
	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	public ImagePanel(final Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, offsetX, offsetY, null);
	}
	
	public void setOffsetX(int offset){
		offsetX = offset;
	}
	
	public void setOffsetY(int offset){
		offsetY = offset;
	}

}
