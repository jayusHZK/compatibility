package com.jayus.smallMyBatis.step13.builder.annotation;

import com.jayus.smallMyBatis.step13.annotations.Delete;
import com.jayus.smallMyBatis.step13.annotations.Insert;
import com.jayus.smallMyBatis.step13.annotations.Select;
import com.jayus.smallMyBatis.step13.annotations.Update;
import com.jayus.smallMyBatis.step13.binding.MapperMethod;
import com.jayus.smallMyBatis.step13.builder.MapperBuilderAssistant;
import com.jayus.smallMyBatis.step13.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step13.mapping.SqlSource;
import com.jayus.smallMyBatis.step13.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.session.ResultHandler;
import com.jayus.smallMyBatis.step13.session.RowBounds;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * @ClassName MapperAnnotationBuilder
 * @Description: 注解配置构建器Mapper
 * @date: 2024/10/14 23:49
 */
public class MapperAnnotationBuilder {

    private final Set<Class<? extends Annotation>> sqlAnnotationTypes = new HashSet<>();

    private Configuration configuration;

    private MapperBuilderAssistant assistant;

    private Class<?> type;

    public MapperAnnotationBuilder(Configuration configuration, Class<?> type) {
        String resource = type.getName().replace(".", "/") + ".java (base guess)";
        this.assistant = new MapperBuilderAssistant(configuration, resource);
        this.configuration = configuration;
        this.type = type;

        sqlAnnotationTypes.add(Select.class);
        sqlAnnotationTypes.add(Insert.class);
        sqlAnnotationTypes.add(Update.class);
        sqlAnnotationTypes.add(Delete.class);
    }

    public void parse() {
        String resource = type.toString();
        if (!configuration.isResourceLoaded(resource)) {
            assistant.setCurrentNameSpace(type.getName());
            Method[] methods = type.getMethods();
            for (Method method : methods) {
                if (!method.isBridge()) {
                    parseStatement(method);
                }
            }
        }
    }

    private void parseStatement(Method method) {
        Class<?> parameterTypeClass = getParameterType(method);
        LanguageDriver languageDriver = getLanguageDriver(method);
        SqlSource sqlSource = getSqlSourceFromAnnotations(method, parameterTypeClass, languageDriver);
        if (sqlSource != null) {
            final String mappedStatementId = type.getName() + "." + method.getName();
            SqlCommandType sqlCommandType = getSqlCommandType(method);
            boolean isSelect = sqlCommandType == SqlCommandType.SELECT;

            String resultMapId = null;
            if (isSelect) {
                resultMapId = parseResultMap(method);
            }
            assistant.addMapperStatement(mappedStatementId, sqlSource, sqlCommandType, parameterTypeClass
                    , resultMapId, getReturnType(method), languageDriver);
        }
    }

    private Class<?> getReturnType(Method method) {
        Class<?> returnType = method.getReturnType();
        if (Collection.class.isAssignableFrom(returnType)) {
            Type returnTypeParameter = method.getGenericReturnType();
            if (returnTypeParameter instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) returnTypeParameter).getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length == 1) {
                    returnTypeParameter = actualTypeArguments[0];
                    if (returnTypeParameter instanceof Class) {
                        returnType = (Class<?>) returnTypeParameter;
                    } else if (returnTypeParameter instanceof ParameterizedType) {
                        returnType = (Class<?>) ((ParameterizedType) returnTypeParameter).getRawType();
                    } else if (returnTypeParameter instanceof GenericArrayType) {
                        Class<?> componentType = (Class<?>) ((GenericArrayType) returnTypeParameter).getGenericComponentType();
                        returnType = Array.newInstance(componentType, 0).getClass();
                    }
                }
            }
        }
        return returnType;
    }

    private String parseResultMap(Method method) {
        StringBuilder suffix = new StringBuilder();
        for (Class<?> c : method.getParameterTypes()) {
            suffix.append("-");
            suffix.append(c.getSimpleName());
        }
        if (suffix.length() < 1) {
            suffix.append("-void");
        }
        String resultMapId = type.getName() + "." + method.getName() + suffix;
        Class<?> returnType = getReturnType(method);
        assistant.addResultMap(resultMapId, returnType, new ArrayList<>());
        return resultMapId;
    }


    private SqlCommandType getSqlCommandType(Method method) {
        Class<? extends Annotation> type = getSqlAnnotationType(method);
        if (type == null) {
            return SqlCommandType.UNKNOWN;
        }
        return SqlCommandType.valueOf(type.getSimpleName().toUpperCase(Locale.ENGLISH));
    }

    private SqlSource getSqlSourceFromAnnotations(Method method, Class<?> parameterType, LanguageDriver languageDriver) {
        try {
            Class<? extends Annotation> sqlAnnotationType = getSqlAnnotationType(method);
            if (sqlAnnotationType != null) {
                Annotation sqlAnnotation = method.getAnnotation(sqlAnnotationType);
                final String[] strings = (String[]) sqlAnnotation.getClass().getMethod("value").invoke(sqlAnnotation);
                return buildSqlSourceFromStrings(strings, parameterType, languageDriver);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private SqlSource buildSqlSourceFromStrings(String[] strings, Class<?> parameterTypeClass, LanguageDriver languageDriver) {
        final StringBuilder sql = new StringBuilder();
        for (String fragment : strings) {
            sql.append(fragment);
            sql.append(" ");
        }
        return languageDriver.createSqlSource(configuration, sql.toString(), parameterTypeClass);
    }

    private Class<? extends Annotation> getSqlAnnotationType(Method method) {
        for (Class<? extends Annotation> type : sqlAnnotationTypes) {
            Annotation annotation = method.getAnnotation(type);
            if (annotation != null) return type;
        }
        return null;
    }

    private LanguageDriver getLanguageDriver(Method method) {
        Class<?> langClass = configuration.getLanguageRegistry().getDefaultDriverClass();
        return configuration.getLanguageRegistry().getDriver(langClass);
    }

    private Class<?> getParameterType(Method method) {
        Class<?> parameterType = null;
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (Class<?> clazz : parameterTypes) {
            if (!RowBounds.class.isAssignableFrom(clazz) && !ResultHandler.class.isAssignableFrom(clazz)) {
                if (parameterType == null) {
                    parameterType = clazz;
                } else {
                    parameterType = MapperMethod.ParamMap.class;
                }
            }
        }
        return parameterType;
    }

}
