package com.jayus.monitor.util;

import java.util.List;

/**
 * @ClassName MethodDesciption
 * @Description: 描述方法详情的类
 * @date: 2024/10/29 19:33
 */
public class MethodDesciption {

    private String className;

    private String methodName;

    private List<String> parameterNameList;

    private List<String> parameterTypeList;

    private String returnType;

    public MethodDesciption() {
    }

    public MethodDesciption(String className, String methodName, List<String> parameterNameList, List<String> parameterTypeList, String returnType) {
        this.className = className;
        this.methodName = methodName;
        this.parameterNameList = parameterNameList;
        this.parameterTypeList = parameterTypeList;
        this.returnType = returnType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<String> getParameterNameList() {
        return parameterNameList;
    }

    public void setParameterNameList(List<String> parameterNameList) {
        this.parameterNameList = parameterNameList;
    }

    public List<String> getParameterTypeList() {
        return parameterTypeList;
    }

    public void setParameterTypeList(List<String> parameterTypeList) {
        this.parameterTypeList = parameterTypeList;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }






}
