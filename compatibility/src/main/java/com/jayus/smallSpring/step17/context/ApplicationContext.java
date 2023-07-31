package com.jayus.smallSpring.step17.context;

import com.jayus.smallSpring.step17.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step17.beans.factory.ListableBeanFactory;
import com.jayus.smallSpring.step17.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/7/31 16:05
 * @description :
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
