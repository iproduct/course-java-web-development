package bulb;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JComponent;

public class SwitchBean extends JComponent implements Serializable {
	private static final long serialVersionUID = 1L;
	// Properties
	private int width = 150;
	private int height = 70;
	private boolean closed = false;
	private List<ActionListener> actionListeners = new CopyOnWriteArrayList<>();

	public SwitchBean() {
		setSize(width, height);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				toggle();
			}
		});
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

	public boolean isClosed() {
		return closed;
	}

	// Property change support for bound property
//	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
//
//	public void addPropertyChangeListener(PropertyChangeListener lis) {
//		changes.addPropertyChangeListener(lis);
//	}
//
//	public void removePropertyChangeListener(PropertyChangeListener lis) {
//		changes.removePropertyChangeListener(lis);
//	}

	public void setClosed(boolean newStatus) {
		boolean oldStatus = closed;
		closed = newStatus;
		firePropertyChange("closed", oldStatus, newStatus);
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 50, 50, 10);
		g.fillRect(100, 50, 50, 10);
		g.fillOval(45, 45, 20, 20);
		g.fillOval(95, 45, 20, 20);
		if (closed)
			g.fillRect(50, 50, 50, 10);
		else
			g.fillPolygon(new int[] { 50, 90, 95, 50 }, new int[] { 50, 20, 30, 65 }, 4);
	}

	public void addActionListener(ActionListener l) {
		actionListeners.add(l);
	}

	public void removeActionListener(ActionListener l) {
		actionListeners.remove(l);
	}

	// Toggle the switch
	public void toggle() {
		setClosed(!isClosed());
		ActionEvent ev = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, closed ? "ON" : "OFF");
		actionListeners.forEach(listener -> {
			listener.actionPerformed(ev);
		});
		repaint();
	}
}