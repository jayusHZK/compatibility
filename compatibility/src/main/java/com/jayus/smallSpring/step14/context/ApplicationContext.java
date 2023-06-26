package com.jayus.smallSpring.step14.context;

import com.jayus.smallSpring.step14.beans.factory.HierarchicalBeanFactory;
import com.jayus.smallSpring.step14.beans.factory.ListableBeanFactory;
import com.jayus.smallSpring.step14.core.io.Resource;
import com.jayus.smallSpring.step14.core.io.ResourceLoader;

/**
 * @author : h zk
 * @date : 2023/6/26 16:56
 * @description :
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
