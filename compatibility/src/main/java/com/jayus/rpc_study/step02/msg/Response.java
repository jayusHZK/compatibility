package com.jayus.rpc_study.step02.msg;

/**
 * @ClassName Response
 * @Description:
 * @date: 2024/10/22 14:00
 */
public class Response {

    private String requestId;

    private String param;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
