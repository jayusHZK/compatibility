package com.jayus.test;

import com.jayus.utils.GZipUtils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : h zk
 * @date : 2022/12/5 17:19
 * @description :
 **/
public class GZipUtil {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        String a = "{\"type\":1,\"aid\":\"11\"}";
        byte[] compress = GZipUtils.compress(a.getBytes());
        byte[] decompress = GZipUtils.decompress(compress);
        System.out.println(new String(decompress));

    }

}
