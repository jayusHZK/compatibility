package com.jayus.rpc_study.step03.network.future;

import com.jayus.rpc_study.step03.network.msg.Response;

import java.util.concurrent.Future;

/**
 * @ClassName WriteFuture
 * @Description:
 * @date: 2024/10/22 21:24
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
