package com.jayus.smallMyBatis.step11.scripting.xmltags;

import com.jayus.smallMyBatis.step11.reflection.MetaObject;
import com.jayus.smallMyBatis.step11.session.Configuration;
import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.OgnlRuntime;
import ognl.PropertyAccessor;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DynamicContext
 * @Description: 动态上下文
 * @date: 2024/9/17 23:44
 */
public class DynamicContext {

    public static final String PARAMETER_OBJECT_KEY = "_parameter";

    public static final String DATABASE_ID_KEY = "_databaseId";

    static {
        OgnlRuntime.setPropertyAccessor(ContextMap.class,new ContextAccessor());
    }

    private final ContextMap bindings;

    private final StringBuilder sqlBuilder = new StringBuilder();

    private int uniqueNumber = 0;

    public DynamicContext(Configuration configuration,Object parameterObject) {
        // 绝大多数调用的地方parameterObject为null
        if (parameterObject != null && !(parameterObject instanceof Map)) {
            // 如果是map型  ??  这句是 如果不是map型
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            bindings = new ContextMap(metaObject);
        } else {
            bindings = new ContextMap(null);
        }
        bindings.put(PARAMETER_OBJECT_KEY,parameterObject);
        bindings.put(DATABASE_ID_KEY,configuration.getDatabaseId());
    }

    public Map<String,Object> getBindings(){
        return bindings;
    }

    public void bind(String name,Object value) {
        bindings.put(name,value);
    }

    public void appendSql(String sql) {
        sqlBuilder.append(sql);
        sqlBuilder.append(" ");
    }

    public String getSql(){
        return sqlBuilder.toString().trim();
    }

    public int getUniqueNumber(){
        return uniqueNumber++;
    }

    // 上下文 map，静态内部类
    static class ContextMap extends HashMap<String,Object> {
        private static final long serialVersionUID = 2977601501966151582L;

        private MetaObject parameterMetaObject;

        public ContextMap(MetaObject parameterMetaObject) {
            this.parameterMetaObject = parameterMetaObject;
        }

        @Override
        public Object get(Object key) {
            String strKey = (String) key;
            // 先去 map 找
            if (super.containsKey(strKey)){
                return super.get(key);
            }
            // 如果没找到，再用 ognl 表达式去取值
            if (parameterMetaObject != null) {
                return parameterMetaObject.getValue(strKey);
            }
            return null;
        }
    }

    // 上下文访问器，静态内部类，实现 ognl 的 propertyAccessor
    static class ContextAccessor implements PropertyAccessor {

        @Override
        public Object getProperty(Map context, Object o, Object o1) throws OgnlException {
            Map map = (Map) o;
            Object result = map.get(o1);
            if (result != null) {
                return result;
            }
            Object parameterObject = map.get(PARAMETER_OBJECT_KEY);
            if (parameterObject instanceof Map) {
                return ((Map<?, ?>) parameterObject).get(o1);
            }
            return null;
        }

        @Override
        public void setProperty(Map context, Object o, Object o1, Object o2) throws OgnlException {
            Map<Object,Object> map = (Map<Object, Object>) o;
            map.put(o1,o2);
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
