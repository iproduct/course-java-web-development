package simple;

import bulb.BulbBean;

import java.beans.BeanDescriptor;

public class TestBean {
    // Specify the target Bean class, and,
	// If the Bean has a customizer, specify it also.
    private final static Class beanClass = BulbBean.class;
    private final static Class customizerClass = BulbBeanCustomizer.class;

    public TestBean() {
    }

    public BeanDescriptor getBeanDescriptor() {
        return new BeanDescriptor(beanClass, customizerClass);
    }


}
