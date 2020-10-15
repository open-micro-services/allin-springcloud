package com.boonya.springcloud.messages.rocketmq.advance.bean;

import java.io.Serializable;

/**
 * @ClassName: Panda
 * @Description: TODO(Panda实体)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2019/10/10 0:23
 */
public class Panda implements Serializable {
    private static final long serialVersionUID = -470078490582001132L;
    private String name;
    private Integer age;

    public Panda() {
    }

    public Panda(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Panda{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
