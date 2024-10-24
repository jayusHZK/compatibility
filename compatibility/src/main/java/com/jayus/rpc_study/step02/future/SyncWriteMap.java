package com.jayus.rpc_study.step02.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SyncWriteMap
 * @Description:
 * @date: 2024/10/22 19:04
 */
public class SyncWriteMap {

    public static Map<String,WriteFuture> syncKey = new ConcurrentHashMap<>();

}
