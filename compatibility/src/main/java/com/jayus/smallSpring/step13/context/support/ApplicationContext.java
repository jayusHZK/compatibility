package com.jayus.smallSpring.step13.context.support;

import com.jayus.smallSpring.step13.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step13.beans.factory.ListableBeanFactory;
import com.jayus.smallSpring.step13.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,
        ApplicationEventPublisher {


}
