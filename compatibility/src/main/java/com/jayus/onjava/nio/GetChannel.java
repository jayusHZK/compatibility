package com.jayus.onjava.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : h zk
 * @date : 2022/8/17 16:24
 * @description :
 **/
public class GetChannel {
    private static String name = "data.txt";
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        try (FileChannel fc = new FileOutputStream(name).getChannel()) {
            // 写入文件
            fc.write(ByteBuffer.wrap("Some text".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // RandomAccessFile 可以移动操作指针
        try (FileChannel fc = new RandomAccessFile(name, "rw").getChannel()) {
            // 在文件尾添加
            fc.position(fc.size()); //移动到结尾
            fc.write(ByteBuffer.wrap("Some more".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileChannel fc = new RandomAccessFile(name, "rw").getChannel()) {
            ByteBuffer buff = ByteBuffer.allocate(BSIZE);
            // ByteBuffer.allocateDirect() //生成与操作系统具备更高耦合度的“直接”缓冲区，但是开销更大
            fc.read(buff);
            buff.flip();
            while (buff.hasRemaining())
                System.out.write(buff.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.flush();
    }
}
