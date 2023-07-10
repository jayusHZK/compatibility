package com.jayus.smallSpring.step15.context;

import com.jayus.smallSpring.step15.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step15.beans.factory.ListableBeanFactory;
import com.jayus.smallSpring.step15.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/7/10 16:29
 * @description :
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
