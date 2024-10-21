package com.jayus.smallMyBatis.step19.builder;

import com.jayus.smallMyBatis.step19.mapping.ResultMap;
import com.jayus.smallMyBatis.step19.mapping.ResultMapping;

import java.util.List;

/**
 * @ClassName ResultMapResolver
 * @Description: 结果映射解析器
 * @date: 2024/10/20 20:48
 */
public class ResultMapResolver {

    private final MapperBuilderAssistant assistant;

    private String id;

    private Class<?> type;

    private List<ResultMapping> resultMappings;

    public ResultMapResolver(MapperBuilderAssistant assistant, String id, Class<?> type, List<ResultMapping> resultMappings) {
        this.assistant = assistant;
        this.id = id;
        this.type = type;
        this.resultMappings = resultMappings;
    }

    public ResultMap resolve(){
        return assistant.addResultMap(this.id,this.type,this.resultMappings);
    }

}
