package com.jayus.smallSpring.step16.context;

import com.jayus.smallSpring.step16.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step16.beans.factory.ListableBeanFactory;
import com.jayus.smallSpring.step16.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/7/17 15:16
 * @description :
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
