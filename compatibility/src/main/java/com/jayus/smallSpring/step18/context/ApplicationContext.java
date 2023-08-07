package com.jayus.smallSpring.step18.context;

import com.jayus.smallSpring.step18.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step18.beans.factory.ListableBeanFactory;
import com.jayus.smallSpring.step18.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
