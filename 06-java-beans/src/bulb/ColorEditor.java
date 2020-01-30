package bulb;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;

public class ColorEditor implements PropertyEditor {
	private Color color;
	public void paintValue(Graphics g, Rectangle box) {
		Color oldColor = g.getColor();
		g.setColor(Color.BLACK);
		g.drawRect(box.x, box.y, box.width - 3, box.height - 3);
		g.setColor(color);
		g.fillRect(box.x + 1, box.y + 1, box.width - 4, box.height - 4);
		g.setColor(oldColor);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAsText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getCustomEditor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJavaInitializationString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPaintable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAsText(String arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValue(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsCustomEditor() {
		// TODO Auto-generated method stub
		return false;
	}
}
