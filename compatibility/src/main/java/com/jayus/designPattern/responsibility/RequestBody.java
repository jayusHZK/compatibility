package com.jayus.designPattern.responsibility;

public class RequestBody {

    private Integer type = 0;

    public RequestBody(Integer type) {
        this.type = type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
