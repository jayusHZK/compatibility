package com.jayus.smallMyBatis.step19.builder;

import com.jayus.smallMyBatis.step19.cache.Cache;
import com.jayus.smallMyBatis.step19.cache.Impl.PerpetualCache;
import com.jayus.smallMyBatis.step19.cache.decorators.FifoCache;
import com.jayus.smallMyBatis.step19.executor.keygen.KeyGenerator;
import com.jayus.smallMyBatis.step19.mapping.*;
import com.jayus.smallMyBatis.step19.reflection.MetaClass;
import com.jayus.smallMyBatis.step19.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step19.session.Configuration;
import com.jayus.smallMyBatis.step19.type.TypeHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName MapperBuilderAssistant
 * @Description: 映射构建器助手，建造者
 * @date: 2024/10/20 13:37
 */
public class MapperBuilderAssistant extends BaseBuilder {

    private String currentNamespace;

    private String resource;

    private Cache currentCache;

    public MapperBuilderAssistant(Configuration configuration, String resource) {
        super(configuration);
        this.resource = resource;
    }

    public ResultMapping buildResultMapping(Class<?> resultType, String property, String column, List<ResultFlag> flags) {
        Class<?> javaTypeClass = resolveResultJavaType(resultType, property, null);
        TypeHandler<?> typeHandlerInstance = resolveTypeHandler(javaTypeClass, null);
        ResultMapping.Builder builder = new ResultMapping.Builder(configuration, property, column, javaTypeClass);
        builder.typeHandler(typeHandlerInstance);
        builder.flags(flags);
        return builder.build();
    }

    private Class<?> resolveResultJavaType(Class<?> resultType, String property, Class<?> javaType) {
        if (javaType == null && property != null) {
            try {
                MetaClass metaResultType = MetaClass.forClass(resultType);
                javaType = metaResultType.getSetterType(property);
            } catch (Exception e) {
            }
        }
        if (javaType == null) {
            javaType = Object.class;
        }
        return javaType;
    }

    public String getCurrentNamespace() {
        return currentNamespace;
    }

    public void setCurrentNamespace(String currentNamespace) {
        this.currentNamespace = currentNamespace;
    }

    public String applyCurrentNamespace(String base, boolean isReference) {
        if (base == null) {
            return null;
        }
        if (isReference) {
            if (base.contains(".")) return base;
        } else {
            if (base.startsWith(currentNamespace + ".")) {
                return base;
            }
            if (base.contains(".")) {
                throw new RuntimeException("Dots are not allowed in element names, please remove it from " + base);
            }
        }
        return currentNamespace + "." + base;
    }

    public MappedStatement addMappedStatement(String id, SqlSource sqlSource, SqlCommandType sqlCommandType, Class<?> parameterType
            , String resultMap, Class<?> resultType, boolean flushCache, boolean useCache, KeyGenerator keyGenerator, String keyProeprty, LanguageDriver lang) {
        id = applyCurrentNamespace(id,false);
        boolean isSelect = sqlCommandType == SqlCommandType.SELECT;
        MappedStatement.Builder statementBuilder = new MappedStatement.Builder(configuration, id,sqlCommandType, sqlSource, resultType);
        statementBuilder.resource(resource);
        statementBuilder.keyGenerator(keyGenerator);
        statementBuilder.keyProperty(keyProeprty);
        setStatementResultMap(resultMap,resultType,statementBuilder);
        setStatementCache(isSelect,flushCache,useCache,currentCache,statementBuilder);
        MappedStatement statement = statementBuilder.build();

        configuration.addMappedStatement(statement);
        return statement;
    }

    private void setStatementCache(boolean isSelect,boolean flushCache,boolean useCache,Cache cache,MappedStatement.Builder statementBuilder) {
        flushCache = valueOrDefault(flushCache, !isSelect);
        useCache = valueOrDefault(useCache,!isSelect);
        statementBuilder.flushCacheRequired(flushCache);
        statementBuilder.useCache(useCache);
        statementBuilder.cache(cache);
    }

    private void setStatementResultMap(String resultMap,Class<?> resultType,MappedStatement.Builder statementBuilder) {
        // 因为暂时还没有在 Mapper XML 中配置 Map 返回结果，所以这里返回的是 null
        resultMap = applyCurrentNamespace(resultMap,true);
        List<ResultMap> resultMaps = new ArrayList<>();
        if (resultMaps != null) {
            String[] resultMapNames = resultMap.split(",");
            for (String resultMapName : resultMapNames) {
                resultMaps.add(configuration.getResultMap(resultMapName.trim()));
            }
        }
        /*
         * 通常使用 resultType 即可满足大部分场景
         * <select id="queryUserInfoById" resultType="cn.bugstack.mybatis.test.po.User">
         * 使用 resultType 的情况下，Mybatis 会自动创建一个 ResultMap，基于属性名称映射列到 JavaBean 的属性上。
         */
         else if (resultType != null){
            ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(configuration, statementBuilder.id() + "-Inline", resultType, new ArrayList<>());
            resultMaps.add(inlineResultMapBuilder.build());
        }
         statementBuilder.resultMaps(resultMaps);
    }

    public ResultMap addResultMap(String id,Class<?> type,List<ResultMapping> resultMappings) {
        id = applyCurrentNamespace(id,false);
        ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(configuration, id, type, resultMappings);
        ResultMap resultMap = inlineResultMapBuilder.build();
        configuration.addResultMap(resultMap);
        return resultMap;
    }

    public Cache useNewCache(Class<? extends Cache> typeClass, Class<? extends Cache> evictionClass, Long flushInterval, Integer size
    , boolean readWrite, boolean blocking, Properties props) {
        typeClass = valueOrDefault(typeClass, PerpetualCache.class);
        evictionClass = valueOrDefault(evictionClass, FifoCache.class);
        Cache cache = new CacheBuilder(currentNamespace)
                .implementation(typeClass)
                .addDecorator(evictionClass)
                .clearInterval(flushInterval)
                .size(size)
                .readWriter(readWrite)
                .blocking(blocking)
                .properties(props)
                .build();
        // 添加缓存
        configuration.addCache(cache);
        currentCache = cache;
        return cache;
    }

    private <T> T valueOrDefault(T value,T defaultValue){
        return value == null ? defaultValue:value;
    }

}