package com.jayus.rpc_study.step02.future;

import com.jayus.rpc_study.step02.msg.Response;

import java.util.concurrent.Future;

/**
 * @ClassName WriteFuture
 * @Description:
 * @date: 2024/10/22 14:05
 */
public interface WriteFuture<T> extends Future<T> {

    Throwable cause();

    void setCause(Throwable cause);

    boolean isWriteSuccess();

    void setWriteResult(boolean result);

    String requestId();

    T response();

    void setResponse(Response response);

    boolean isTimeout();

}
