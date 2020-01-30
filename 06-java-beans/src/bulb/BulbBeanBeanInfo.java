package bulb;
import java.beans.*;

public class BulbBeanBeanInfo extends SimpleBeanInfo {
	   
	   // This beaninfo class is meant for the following bean
	   private final static Class<BulbBean> beanClass = BulbBean.class;
	   
	   public BeanDescriptor getBeanDescriptor() {
	      return new BeanDescriptor(beanClass);
	   }
	   
	   // Publish the "properties" available to builder tools
	   public PropertyDescriptor[] getPropertyDescriptors() {
	      try {
	         PropertyDescriptor onColor =
	            new PropertyDescriptor("onColor", beanClass, "getColor", "setColor");
	         onColor.setBound(true);
	         PropertyDescriptor on =
	            new PropertyDescriptor("on", beanClass);
	         on.setBound(true);
	         PropertyDescriptor rv[] = {onColor, on};
	         return rv;
	      } catch (IntrospectionException e) {
	         throw new Error(e.toString());
	      }
	   }
	   public int getDefaultPropertyIndex() { return 1; }
	}