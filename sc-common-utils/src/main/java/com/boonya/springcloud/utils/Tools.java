package com.boonya.springcloud.utils;

import java.util.Collection;

/**
 * @ClassName: Tools
 * @Description: TODO(功能描述)
 * @author: pengjunlin
 * @company: 上海势航网络科技有限公司
 * @date 2018-12-14
 */
public class Tools {

    /**
     * 检测字符串是否为空(null,"","null")
     *
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals(s.trim()) || "null".equals(s);
    }

    public static boolean isEmpty(Collection<?> c) {
        return (c == null || c.size() == 0) ? true : false;
    }

}
