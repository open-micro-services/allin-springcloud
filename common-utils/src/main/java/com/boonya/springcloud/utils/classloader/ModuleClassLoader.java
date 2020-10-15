package com.boonya.springcloud.utils.classloader;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ModuleClassLoader
 * @Description: TODO(功能说明 : 【 自定义模块加载器 】 - ClassLoader需要注意双亲委派加载机制)--class加解密操作
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/27 23:36
 * @note 在《类加载器》中讲的，默认类加载器只能加载固定路径下的class，如果有特定路径下的class，需要自定义【安全性】：系统自身需要一些jar，class，如果业务类代码中也有相同的class，破坏系统，类似双亲委托安全性
 */
@Slf4j
public class ModuleClassLoader extends URLClassLoader {

    private ModuleManager manager;

    public ModuleClassLoader(URL[] urls, ModuleManager manager) {
        super(urls);
        this.manager = manager;
    }

    /**
     * 重写符合双亲委派类加载原则方法【确保程序稳定性parent】
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //return super.findClass(name);
        return manager.getCache().get(name);
    }


    /**
     * 测试类加载
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        List<String> jarList = new ArrayList<String>();
        jarList.add("D:/demo-0.0.1-SNAPSHOT.jar");

        ModuleManager manager = new ModuleManager();
        manager.setModuleList(jarList);
        manager.init();

        ModuleClassLoader classLoader = new ModuleClassLoader(((URLClassLoader) ModuleClassLoader.class.getClassLoader()).getURLs(), manager);
        Class<?> moduleA = classLoader.loadClass("module.ModuleService");
        Method sayHelloModuleA = moduleA.getMethod("sayHelloModule");
        String result = (String) sayHelloModuleA.invoke(moduleA.newInstance(), null);
        log.info(result);
    }


}
