package beandemo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class BulbBean extends JComponent {
	private int width = 150;
	private int height = 150;
	
	private static final Color COLOR_OFF = Color.BLACK;
	private Color color = Color.ORANGE;
	
	private boolean on = false;
	
	public BulbBean() {
		setSize(width, height);
		setBackground(Color.GRAY);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}
	
	@Override
	public void paint(Graphics g) {
		if(on) {
			g.setColor(color);
		} else {
			g.setColor(COLOR_OFF);
		}
		g.fillOval(10, 10, width-20, height-20);
	}
	
	public void switchOn() {
		setOn(true);
		repaint();
	}
	
	public void switchOff() {
		setOn(false);
		repaint();
	}
	
	public void toggle() {
		setOn(!isOn());
		repaint();
	}
	
	
	
	

}
