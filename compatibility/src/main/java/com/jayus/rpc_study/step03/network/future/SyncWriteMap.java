package com.jayus.rpc_study.step03.network.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SyncWriteMap
 * @Description:
 * @date: 2024/10/22 21:26
 */
public class SyncWriteMap {

    public static Map<String,WriteFuture> syncKey = new ConcurrentHashMap<>();

}
