package com.boonya.socket.rpc;

import com.boonya.socket.rpc.proxy.RpcClientProxy;

/**
 * @Copyright: 2019-2021
 * @FileName: RpcClient.java
 * @Author: PJL
 * @Date: 2021/1/22 14:58
 * @Description: RPC客户端
 */
public class RpcClient {

    public static void main(String[] args) {
        RpcClientProxy client = new RpcClientProxy<>(IMessage.class, "localhost", "6666");
        IMessage hello = (IMessage) client.getClientIntance();
        System.out.println(hello.getMessage("socket rpc"));
    }
}
