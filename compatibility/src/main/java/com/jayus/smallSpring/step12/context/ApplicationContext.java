package com.jayus.smallSpring.step12.context;

import com.jayus.smallSpring.step12.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step12.beans.factory.ListableBeanFactory;
import com.jayus.smallSpring.step12.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/4/12 11:26
 * @description :
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {

}
