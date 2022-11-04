package com.jayus.onjava.nio;

import sun.security.util.Length;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;

/**
 * @author : h zk
 * @date : 2022/8/17 17:44
 * @description :
 **/
public class LargeMappedFiles {
    static int length = 0x8000000;
    public static void main(String[] args) {
        try (RandomAccessFile tdat = new RandomAccessFile("test.dat", "w")) {
            // 指定要在文件中映射的区域的起始点和长度—这意味着你可以选择映射大文件的较小区域。
            MappedByteBuffer out = tdat.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
            for (int i = 0; i < length; i++) {
                out.put((byte) 'x');
            }
            System.out.println("Finished writing");
            for (int i = length / 2; i < length / 2 + 6; i++) {
                System.out.println(out.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
