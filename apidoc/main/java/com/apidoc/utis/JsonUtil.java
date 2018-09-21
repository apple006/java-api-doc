package com.apidoc.utis;

import com.alibaba.fastjson.JSON;

/**
 * json工具类
 */
public class JsonUtil {

    /**
     * 把对象转换成json字符串
     *
     * @param object 对象
     * @return String
     */
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

}
