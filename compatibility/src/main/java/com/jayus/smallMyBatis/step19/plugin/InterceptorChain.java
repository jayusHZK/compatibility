package com.jayus.smallMyBatis.step19.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName InterceptorChain
 * @Description: 拦截器链
 * @date: 2024/10/18 00:02
 */
public class InterceptorChain {

    private final List<Interceptor> interceptors = new ArrayList<>();

    public Object pluginAll(Object target){
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public List<Interceptor> getInterceptors(){
        return Collections.unmodifiableList(interceptors);
    }

}
