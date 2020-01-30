package bulb;

import java.awt.*;
import java.io.Serializable;

import javax.swing.JComponent;

public class BulbBean extends JComponent implements Serializable {
	private static final long serialVersionUID = 1L;
	// Properties
	private int width = 150;
	private int height = 150;

	private static final Color COLOR_OFF = Color.BLACK;
	private Color color = Color.ORANGE; // property with a default value

	boolean on = false; // property with a default value

	public BulbBean() { // constructor
		setSize(150, 150);
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
	} // getter

	public void setColor(Color color) {
		this.color = color;
	} // setter

	public boolean isOn() {
		return on;
	} // getter for boolean

	public void setOn(boolean on) {
		this.on = on;
	} // setter

	// Override the paint() method to draw the LightBulb
	public void paint(Graphics g) {
		if (on)
			g.setColor(color);
		else
			g.setColor(COLOR_OFF);
		g.fillOval(10, 10, 120, 120);
	}

	public void switchOn() { // switch on the Light
		on = true;
		repaint();
	}

	public void switchOff() { // switch off the Light
		on = false;
		repaint();
	}

	public void toggle() { // If on turns off; else turns on
		on = !on;
		repaint();
	}
}