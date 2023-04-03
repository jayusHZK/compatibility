package com.jayus.smallSpring.step10.context;

import com.jayus.smallSpring.step10.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step10.beans.factory.ListableBeanFactory;
import com.jayus.smallSpring.step10.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/4/3 13:48
 * @description :
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
