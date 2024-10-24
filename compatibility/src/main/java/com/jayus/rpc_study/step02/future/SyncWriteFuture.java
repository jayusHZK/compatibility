package com.jayus.rpc_study.step02.future;

import com.jayus.rpc_study.step02.msg.Response;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName SyncWriteFuture
 * @Description:
 * @date: 2024/10/22 14:05
 */
public class SyncWriteFuture implements WriteFuture<Response> {

    private CountDownLatch latch = new CountDownLatch(1);

    private final long begin = System.currentTimeMillis();

    private long timeout;

    private Response response;

    private final String requestId;

    private boolean writeResult;

    private Throwable cause;

    private boolean isTimeout = false;

    public SyncWriteFuture(String requestId) {
        this.requestId = requestId;
    }

    public SyncWriteFuture(String requestId, long timeout) {
        this.timeout = timeout;
        this.requestId = requestId;
        writeResult = true;
        isTimeout = false;
    }

    public Throwable cause(){
        return cause;
    }

    @Override
    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public boolean isWriteSuccess() {
        return writeResult;
    }

    @Override
    public void setWriteResult(boolean writeResult) {
        this.writeResult = writeResult;
    }

    public Response response() {
        return response;
    }

    public String requestId() {
        return requestId;
    }

    @Override
    public void setResponse(Response response) {
        this.response = response;
        latch.countDown();
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return true;
    }

    public boolean isCancelled(){
        return false;
    }

    public boolean isDone(){
        return false;
    }

    public Response get() throws InterruptedException {
        latch.wait();
        return response;
    }

    @Override
    public Response get(long timeout, @NotNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (latch.await(timeout,unit)) {
            return response;
        }
        return null;
    }

    public boolean isTimeout(){
        if (isTimeout) {
            return isTimeout;
        }
        return System.currentTimeMillis() - begin > timeout;
    }

}
