package com.jayus.rpc_study.step02.msg;

/**
 * @ClassName Request
 * @Description:
 * @date: 2024/10/22 14:02
 */
public class Request {

    private String requestId;

    private Object result;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
