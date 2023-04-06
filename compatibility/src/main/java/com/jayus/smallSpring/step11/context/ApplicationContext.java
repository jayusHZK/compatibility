package com.jayus.smallSpring.step11.context;

import com.jayus.smallSpring.step11.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step11.beans.factory.ListableBeanFactory;
import com.jayus.smallSpring.step11.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/4/6 17:08
 * @description :
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory
, ResourceLoader,ApplicationEventPublisher {
}
