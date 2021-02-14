package com.boonya.ffmpeg.command;

import java.io.*;
import java.util.List;

public class ProcessBuilderCommand {

    /**
     * 获取执行命令线程
     *
     * @param commandList
     * @return
     */
    public static Process getProcess(List<String> commandList){
        try {
            return new ProcessBuilder(commandList).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取命令行输出到控制台
     * @param process
     */
    public static void printIntoConsole(Process process){
        InputStream is = process.getInputStream();
        try {
            InputStreamReader inst = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(inst);//输入流缓冲区
            String res;
            StringBuilder sb = new StringBuilder();
            while ((res = br.readLine()) != null) {//循环读取缓冲区中的数据
                sb.append(res+"\n");
            }
            System.out.println(sb.toString());
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //关闭流
            if(is != null){
                try{
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
