package com.jayus.rpc_study.step02.test.client;

import com.alibaba.fastjson.JSON;
import com.jayus.rpc_study.step02.client.ClientSocket;
import com.jayus.rpc_study.step02.future.SyncWrite;
import com.jayus.rpc_study.step02.msg.Request;
import com.jayus.rpc_study.step02.msg.Response;
import io.netty.channel.ChannelFuture;

/**
 * @ClassName StartClient
 * @Description:
 * @date: 2024/10/22 19:20
 */
public class StartClient {

    private static ChannelFuture future;

    public static void main(String[] args) {
        ClientSocket client = new ClientSocket();
        new Thread(client).start();

        while (true) {
            try {
                if (null == future) {
                    future = client.getFuture();
                    Thread.sleep(500);
                    continue;
                }
                Request request = new Request();
                request.setResult("select user info");
                SyncWrite s = new SyncWrite();
                Response response = s.writeAndSync(future.channel(), request, 1000);
                System.out.println("调用结果： "+ JSON.toJSON(response));
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
