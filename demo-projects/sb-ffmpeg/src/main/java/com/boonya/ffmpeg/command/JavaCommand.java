package com.boonya.ffmpeg.command;

import java.util.Arrays;
import java.util.List;

/**
 * Java命令行输出工具
 */
public class JavaCommand {

    /**
     * 执行Javac编译.java文件
     *
     * @param javaFile
     */
    public static void javac(String javaFile){
        List<String> cmdList = Arrays.asList("javac",javaFile);
        Process process = ProcessBuilderCommand.getProcess(cmdList);
        ProcessBuilderCommand.printIntoConsole(process);
    }

    /**
     * 执行编译文件
     *
     * @param className
     */
    public static void runClass(String className){
        List<String> cmdList = Arrays.asList("java",className);
        Process process = ProcessBuilderCommand.getProcess(cmdList);
        ProcessBuilderCommand.printIntoConsole(process);
    }
}
