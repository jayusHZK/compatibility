package com.jayus.onjava.nio;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * @author : h zk
 * @date : 2022/8/17 17:57
 * @description : 文件锁
 *
 **/
public class FileLocking {
    public static void main(String[] args) {
        try(FileOutputStream fos = new FileOutputStream("file.txt");
            FileLock fl = fos.getChannel().tryLock()){
            /*
            lock(long position, long size, boolean shared)
            锁定文件的一部分，锁住 size-position 区域。第三个参数指定是否共享此锁。
             */
            //fos.getChannel().lock()
            if (fl != null) {
                System.out.println("Locked File");
                TimeUnit.MILLISECONDS.sleep(100);
                fl.release();//释放锁
                System.out.println("Release Lock");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
