package com.jayus.file.read;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author : h zk
 * @date : 2023/5/19 14:48
 * @description :
 **/
public class ReadLocalFile {

    public static void main(String[] args) {
        Path path = null;
        File file = new File("D:\\d");
        if (file.isDirectory()) {
            for (File item : file.listFiles()) {
                System.out.println(item.getName());
                // 在这一层判断 类别是否存在
                //  type exist
                if (item.isDirectory()) {
                    for (File itam : item.listFiles()) {
                        for (File itbm : itam.listFiles()) {
                                // 读取json 文件内容
                                if (itbm.getName().contains("json")) {
                                    path = Paths.get(itbm.getAbsolutePath());
                                    try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
                                        ByteBuffer buffer = ByteBuffer.allocate(1024 * 10);
                                        while (channel.read(buffer) > 0) {
                                            buffer.flip();
                                            // 处理缓冲区中的数据
                                            String content = new String(buffer.array(), 0, buffer.limit());
                                            ReadLocalFileVO readLocalFileVO = JSONObject.parseObject(content, ReadLocalFileVO.class);
                                            buffer.clear(); // 清空缓冲区
                                            buffer.flip();
                                            if (StringUtils.isNotEmpty(readLocalFileVO.getMobile_file_2k())) {
                                                System.out.println(readLocalFileVO.getMobile_file_2k());
                                            } else if (StringUtils.isNotEmpty(readLocalFileVO.getMobile_file_1k())) {
                                                System.out.println(readLocalFileVO.getMobile_file_1k());
                                            }
                                            if (StringUtils.isNotEmpty(readLocalFileVO.getMobile_file_640())) {
                                                System.out.println(readLocalFileVO.getMobile_file_640());
                                            }
                                            if (StringUtils.isNotEmpty(readLocalFileVO.getMobile_file_320())) {
                                                System.out.println(readLocalFileVO.getMobile_file_320());
                                            }
                                            //System.out.print(content);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                        }
                    }
                }
            }
        }
    }

}
