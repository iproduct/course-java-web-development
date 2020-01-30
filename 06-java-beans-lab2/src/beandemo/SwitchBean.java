package beandemo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JComponent;

public class SwitchBean extends JComponent{
	private static final long serialVersionUID = 1L;
	
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
	
	@Override
	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	@Override
	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) throws PropertyVetoException {
		boolean oldValue = this.closed;
		this.closed = closed;
		fireVetoableChange("closed", oldValue, this.closed);
		firePropertyChange("closed", oldValue, this.closed);
		notifyActionListeners();

	}
	
	@Override
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


	public void toggle() {
		try {
			setClosed(!isClosed());
		} catch (PropertyVetoException e) {
			System.out.println("Property 'closed' veto received: " + e.getMessage());
		}
		repaint();
	}
	
	public void addActionListener(ActionListener al) {
		actionListeners.add(al);
	}
	
	public void removeActionListener(ActionListener al) {
		actionListeners.remove(al);
	}
	
	protected void notifyActionListeners() {
		actionListeners.forEach(listener -> listener.actionPerformed(
				new ActionEvent(this, ActionEvent.ACTION_PERFORMED, isClosed() ? "ON" : "OFF")));
	}
	
	
}
