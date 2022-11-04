package com.jayus.onjava.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author : h zk
 * @date : 2022/8/17 16:54
 * @description :
 **/
public class TransferTo {
    public static void main(String[] args) {
        Charset.availableCharsets();
        try (FileChannel in = new FileInputStream(
                args[0]).getChannel();
             FileChannel out = new FileOutputStream(
                     args[1]).getChannel()) {
            in.transferTo(0,in.size(),out); //直接连接此通道到彼通道
            //out.transferFrom(in,0,in.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
