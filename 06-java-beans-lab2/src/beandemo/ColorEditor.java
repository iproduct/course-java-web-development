package beandemo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyEditorSupport;

public class ColorEditor extends PropertyEditorSupport {
//	private Color color;
//	
//	@Override
//	public void paintValue(Graphics g, Rectangle box) {
//		Color oldColor = g.getColor();
//		g.setColor(Color.BLACK);
//		g.drawRect(box.x, box.y, box.width - 3, box.height - 3);
//		g.setColor(color);
//		g.fillRect(box.x + 1, box.y + 1, box.width - 4, box.height - 4);
//		g.setColor(oldColor);
//	}

	protected static String colorNames[] = { "white", "lightGray", "gray", "darkGray", "black", "red", "pink", "orange",
			"yellow", "green", "magenta", "cyan", "blue" };

	// Used to map a color name to a Color object
	private static Color colors[] = { Color.white, Color.lightGray, Color.gray, Color.darkGray, Color.black, Color.red,
			Color.pink, Color.orange, Color.yellow, Color.green, Color.magenta, Color.cyan, Color.blue };

	// The currently-selected color (start with white)
	private int selected = 0;

	// Tells the bean builder the name of the current color
	@Override
	public String getAsText() {
		return colorNames[selected];
	}

	// Allows the bean builder to tell the property editor the value
	// that the user has entered
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		for (selected = 0; selected < colorNames.length && !colorNames[selected].equals(text); selected++)
			;
		if (selected == colorNames.length)
			selected = 0;
	}

	// Tells the bean builder the value for the property
	@Override
	public Object getValue() {
		return colors[selected];
	}

	// Allows the bean builder to pass the current property
	// value to the property editor
	@Override
	public void setValue(Object value) {
		selected = 0;
		if (value != null)
			for (int i = 0; i < colors.length; i++)
				if (value.equals(colors[i])) {
					selected = i;
					break;
				}
	}

	// Get the initialization code for the property
	@Override
	public String getJavaInitializationString() {
		return "java.awt.Color." + colorNames[selected];
	}
}
