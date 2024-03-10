package com.jayus.smallMyBatis.step08.scripting.xmltags;

import com.jayus.smallMyBatis.step08.reflection.MetaObject;
import com.jayus.smallMyBatis.step08.session.Configuration;
import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.OgnlRuntime;
import ognl.PropertyAccessor;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态上下文
 */
public class DynamicContext {

    private static final String PARAMETER_OBJECT_KEY = "_parameter";

    private static final String DATASOURCE_ID_KEY = "_databaseId";

    static {
        // 定义属性->getter方法映射，ContextMap到ContextAccessor的映射，注册到ognl运行时
        // 参考http://commons.apache.org/proper/commons-ognl/developer-guide.html
        OgnlRuntime.setPropertyAccessor(ContextMap.class,new ContextAccessor());
        // 将传入的参数对象统一封装为ContextMap对象（继承了HashMap对象），
        // 然后Ognl运行时环境在动态计算sql语句时，
        // 会按照ContextAccessor中描述的Map接口的方式来访问和读取ContextMap对象，获取计算过程中需要的参数。
        // ContextMap对象内部可能封装了一个普通的POJO对象，也可以是直接传递的Map对象，当然从外部是看不出来的，因为都是使用Map的接口来读取数据。
    }

    private final ContextMap bindings;

    private final StringBuilder sqlBuilder = new StringBuilder();

    private int uniqueNumber = 0;

    // 在DynamicContext的构造函数中，根据传入的参数对象是否为Map类型，有两个不同构造ContextMap的方式。
    // 而ContextMap作为一个继承了HashMap的对象，作用就是用于统一参数的访问方式：用Map接口方法来访问数据。
    // 具体来说，当传入的参数对象不是Map类型时，Mybatis会将传入的POJO对象用MetaObject对象来封装，
    // 当动态计算sql过程需要获取数据时，用Map接口的get方法包装 MetaObject对象的取值过程。
    public DynamicContext(Configuration configuration,Object parameterObject) {
        // 绝大多数调用的地方parameterObject为null
        if (parameterObject != null && !(parameterObject instanceof Map)){
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            bindings = new ContextMap(metaObject);
        } else {
            bindings = new ContextMap(null);
        }
        bindings.put(PARAMETER_OBJECT_KEY,parameterObject);
        bindings.put(DATASOURCE_ID_KEY,configuration.getDatabaseId());
    }

    public ContextMap getBindings() {
        return bindings;
    }

    public void bind(String name,Object value){
        bindings.put(name,value);
    }

    public void appendSql(String sql){
        sqlBuilder.append(sql);
        sqlBuilder.append(" ");
    }

    public String getSql(){
        return sqlBuilder.toString().trim();
    }

    public int getUniqueNumber() {
        return uniqueNumber++;
    }

    /**
     * 上下文 map 静态内部类
     */
    static class ContextMap extends HashMap<String,Object> {
        private static final long serialVersionUID = 2977601501966151582L;

        private MetaObject parameterMetaObject;

        public ContextMap(MetaObject parameterMetaObject){
            this.parameterMetaObject = parameterMetaObject;
        }

        @Override
        public Object get(Object key) {
            String strKey= (String) key;
            // 现在 map 找
            if (super.containsKey(strKey)) {
                return super.get(key);
            }
            // 如果没找到 再用 ognl 表达式去取值
            if (parameterMetaObject != null){
                return parameterMetaObject.getValue(strKey);
            }
            return null;
        }
    }

    /**
     * 上下文访问器 静态内部类 实现 OGNL 的 PropertyAccessor
     */
    static class ContextAccessor implements PropertyAccessor {

        @Override
        public Object getProperty(Map context, Object target, Object name) throws OgnlException {
            Map map = (Map) target;
            Object result = map.get(name);
            if (result != null){
                return result;
            }
            Object parameterObject = map.get(PARAMETER_OBJECT_KEY);
            if (parameterObject instanceof Map){
                return ((Map<?, ?>) parameterObject).get(name);
            }
            return null;
        }

        @Override
        public void setProperty(Map context, Object target, Object name, Object value) throws OgnlException {
            Map<Object,Object > map = (Map<Object, Object>) target;
            map.put(name,value);
        }

        @Override
        public String getSourceAccessor(OgnlContext ognlContext, Object o, Object o1) {
            return null;
        }

        @Override
        public String getSourceSetter(OgnlContext ognlContext, Object o, Object o1) {
            return null;
        }
    }

}
