package com.jayus.smallMyBatis.step12.reflection.wrapper;

import com.jayus.smallMyBatis.step12.reflection.MetaObject;
import com.jayus.smallMyBatis.step12.reflection.SystemMetaObject;
import com.jayus.smallMyBatis.step12.reflection.factory.ObjectFactory;
import com.jayus.smallMyBatis.step12.reflection.property.PropertyTokenizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MapWrapper
 * @Description: Map 包装器
 * @date: 2024/9/20 08:15
 */
public class MapWrapper extends BaseWrapper {

    // 原来的对象
    private Map<String,Object> map;

    public MapWrapper(MetaObject metaObject,Map<String, Object> map) {
        super(metaObject);
        this.map = map;
    }

    // get,set是允许的，
    @Override
    public Object get(PropertyTokenizer prop) {
        if (prop.getIndex() != null) {
            Object collection = resolveCollection(prop, map);
            return getCollectionValue(prop,collection);
        } else {
            return map.get(prop.getName());
        }
    }

    @Override
    public void set(PropertyTokenizer prop, Object value) {
        if (prop.getIndex() != null){
            Object collection = resolveCollection(prop, map);
            setCollectionValue(prop,collection,value);
        } else {
            map.put(prop.getName(),value);
        }
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        return name;
    }

    @Override
    public String[] getGetterNames() {
        return map.keySet().toArray(new String[map.keySet().size()]);
    }

    @Override
    public String[] getSetterNames() {
        return map.keySet().toArray(new String[map.keySet().size()]);
    }

    @Override
    public Class<?> getSetterType(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            MetaObject metaValue = metaObject.metaObjectForProperty(prop.getIndexedName());
            if (metaValue == SystemMetaObject.NULL_META_OBJECT) {
                return Object.class;
            } else {
                return metaValue.getSetterType(prop.getChildren());
            }
        } else {
            if (map.get(name) != null) {
                return map.get(name).getClass();
            } else {
                return Object.class;
            }
        }
    }

    @Override
    public Class<?> getGetterType(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            MetaObject metaValue = metaObject.metaObjectForProperty(prop.getIndexedName());
            if (metaValue == SystemMetaObject.NULL_META_OBJECT) {
                return Object.class;
            } else {
                return metaValue.getGetterType(prop.getChildren());
            }
        } else {
            if (map.get(name) != null) {
                return map.get(name).getClass();
            } else {
                return Object.class;
            }
        }
    }

    @Override
    public boolean hasSetter(String name) {
        return true;
    }

    @Override
    public boolean hasGetter(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            if (map.containsKey(prop.getIndexedName())) {
                MetaObject metaValue = metaObject.metaObjectForProperty(prop.getIndexedName());
                if (metaValue == SystemMetaObject.NULL_META_OBJECT) {
                    return true;
                } else {
                    return metaValue.hasGetter(prop.getChildren());
                }
            } else {
                return false;
            }
        } else {
            return map.containsKey(prop.getName());
        }
    }

    @Override
    public MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory) {
        HashMap<String,Object> map = new HashMap<>();
        set(prop,map);
        return MetaObject.forObject(map, metaObject.getObjectFactory(), metaObject.getObjectWrapperFactory());
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public void add(Object element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E> void addAll(List<E> element) {
        throw new UnsupportedOperationException();
    }
}
