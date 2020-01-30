package beandemo;

import java.beans.BeanDescriptor;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class BulbBeanBeanInfo extends SimpleBeanInfo {

	// This beaninfo class is meant for the following bean
	private final static Class<BulbBean> beanClass = BulbBean.class;

	public BeanDescriptor getBeanDescriptor() {
		return new BeanDescriptor(beanClass) {
		};
	}

	// Publish the "properties" available to builder tools
	public PropertyDescriptor[] getPropertyDescriptors() {
		try {
			PropertyDescriptor onColor = new PropertyDescriptor("onColor", beanClass, "getColor", "setColor");
			onColor.setBound(true);
			onColor.setPropertyEditorClass(ColorEditor.class);
			PropertyDescriptor on = new PropertyDescriptor("on", beanClass);
			on.setBound(true);
			PropertyDescriptor rv[] = { onColor, on };
			return rv;
		} catch (IntrospectionException e) {
			throw new Error(e.toString());
		}
	}

	public int getDefaultPropertyIndex() {
		return 1;
	}

	// Expose only the selected methods to the Builder tool
	@Override
	public MethodDescriptor[] getMethodDescriptors() {

		Class<?> args[] = {}; // argument of the method
		// Use "Class args[] = { java.util.EventObject.class };"
		// if the method takes Event as sole argument.

		try {
			MethodDescriptor toggle = new MethodDescriptor(beanClass.getMethod("toggle", args));
			MethodDescriptor switchOn = new MethodDescriptor(beanClass.getMethod("switchOn", args));
			MethodDescriptor switchOff = new MethodDescriptor(beanClass.getMethod("switchOff", args));
			MethodDescriptor result[] = { toggle, switchOn, switchOff };
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// publish the "events" availeble to builder tools
	public EventSetDescriptor[] getEventSetDescriptors() {
		try {
			// To expose MouseEvent
			String[] mouseListenerMethods = { "mouseClicked", "mousePressed", "mouseReleased", "mouseEntered",
					"mouseExited" };
			EventSetDescriptor mouse = new EventSetDescriptor(beanClass, "mouse", java.awt.event.MouseListener.class,
					mouseListenerMethods, "addMouseListener", "removeMouseListener");
			mouse.setDisplayName("mouse");

			EventSetDescriptor[] rv = { mouse };
			return rv;
		} catch (IntrospectionException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}