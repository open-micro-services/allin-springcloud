package com.boonya.ffmpeg.command;

import java.io.IOException;

/**
 * Runtime类是Java程序的运行时环境
 */
public class RuntimeCommand {

    /**
     * 执行完整命令行
     *
     * 不能new出一个Runtime对象，只能通过getRuntime()方法获取当前Runtime运行时对象的引用。
     * 可以调用Runtime的方法查看和修改Java虚拟机的状态。
     * @param command
     */
    public static void exec(String command){
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Windows查看IP地址
        RuntimeCommand.exec("ipconfig");
        // Linux查看IP地址
        RuntimeCommand.exec("ifconfig");
    }
}
