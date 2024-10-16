package com.jayus.smallMyBatis.step12.script.xmltags;

import com.jayus.smallMyBatis.step12.reflection.MetaObject;
import com.jayus.smallMyBatis.step12.session.Configuration;
import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.OgnlRuntime;
import ognl.PropertyAccessor;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DynamicContext
 * @Description: 动态上下文
 * @date: 2024/9/26 00:54
 */
public class DynamicContext {

    public static final String PARAMETER_OBJECT_KEY = "_parameter";

    public static final String DATABASE_ID_KEY = "_databaseId";

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

    public DynamicContext(Configuration configuration,Object parameterObject) {
        if (parameterObject != null && !(parameterObject instanceof Map)) {
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

    public int getUniqueNumber() {
        return uniqueNumber++;
    }

    static class ContextMap extends HashMap<String,Object> {
        private static final long serialVersionUID = 2977601501966151582L;

        private MetaObject parameterMetaObject;

        public ContextMap(MetaObject parameterMetaObject) {
            this.parameterMetaObject = parameterMetaObject;
        }

        @Override
        public Object get(Object key) {
            String strKey = (String) key;
            // 先去map里找
            if (super.containsKey(strKey)) {
                return super.get(strKey);
            }
            if (parameterMetaObject != null) {
                return parameterMetaObject.getValue(strKey);
            }
            return null;
        }
    }

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
            map.put(o1, o2);
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
