package com.apidoc.utis;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * @Description: SpringBoot 相关工具类
 * @Author: peng.liu
 * @CreateDate: 2018/7/25 1:17
 */
public class SpringUtil {

    /**
     * 解析SpringBoot的路由注解 得到路由映射mapping
     * 解析以下注解
     *
     * @param claszz 类
     * @see RequestMapping
     * @see GetMapping
     * @see PostMapping
     * @see PutMapping
     * @see DeleteMapping
     * @see PatchMapping
     * 查看spring源码可以看到，RequestMapping中有多个同等意义的属性，这里分别处理
     * value name path
     */
    public static String getMapping(Class claszz) {
        RequestMapping requestMapping = (RequestMapping) claszz.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            //
            if (StringUtil.isNotEmpty(requestMapping.value())) {
                return requestMapping.value()[0];
            }
            if (StringUtil.isNotEmpty(requestMapping.name())) {
                return requestMapping.name();
            }
            if (StringUtil.isNotEmpty(requestMapping.path())) {
                return requestMapping.path()[0];
            }
        }

        GetMapping getMapping = (GetMapping) claszz.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            if (StringUtil.isNotEmpty(getMapping.value())) {
                return getMapping.value()[0];
            }
            if (StringUtil.isNotEmpty(getMapping.name())) {
                return getMapping.name();
            }
            if (StringUtil.isNotEmpty(getMapping.path())) {
                return getMapping.path()[0];
            }
        }

        PostMapping postMapping = (PostMapping) claszz.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            if (StringUtil.isNotEmpty(postMapping.value())) {
                return postMapping.value()[0];
            }
            if (StringUtil.isNotEmpty(postMapping.name())) {
                return postMapping.name();
            }
            if (StringUtil.isNotEmpty(postMapping.path())) {
                return postMapping.path()[0];
            }
        }

        PutMapping putMapping = (PutMapping) claszz.getAnnotation(PutMapping.class);
        if (putMapping != null) {
            if (StringUtil.isNotEmpty(putMapping.value())) {
                return putMapping.value()[0];
            }
            if (StringUtil.isNotEmpty(putMapping.name())) {
                return putMapping.name();
            }
            if (StringUtil.isNotEmpty(putMapping.path())) {
                return putMapping.path()[0];
            }
        }

        DeleteMapping deleteMapping = (DeleteMapping) claszz.getAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            if (StringUtil.isNotEmpty(deleteMapping.value())) {
                return deleteMapping.value()[0];
            }
            if (StringUtil.isNotEmpty(deleteMapping.name())) {
                return deleteMapping.name();
            }
            if (StringUtil.isNotEmpty(deleteMapping.path())) {
                return deleteMapping.path()[0];
            }
        }
        return null;
    }

    /**
     * 解析SpringBoot的路由注解 得到路由映射mapping
     * 解析以下注解
     *
     * @param method 方法
     * @see RequestMapping
     * @see GetMapping
     * @see PostMapping
     * @see PutMapping
     * @see DeleteMapping
     * @see PatchMapping
     * 查看spring源码可以看到，RequestMapping中有多个同等意义的属性，这里分别处理
     * value name path
     */
    public static String getMapping(Method method) {
        RequestMapping requestMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            //
            if (StringUtil.isNotEmpty(requestMapping.value())) {
                return requestMapping.value()[0];
            }
            if (StringUtil.isNotEmpty(requestMapping.name())) {
                return requestMapping.name();
            }
            if (StringUtil.isNotEmpty(requestMapping.path())) {
                return requestMapping.path()[0];
            }
        }

        GetMapping getMapping = (GetMapping) method.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            if (StringUtil.isNotEmpty(getMapping.value())) {
                return getMapping.value()[0];
            }
            if (StringUtil.isNotEmpty(getMapping.name())) {
                return getMapping.name();
            }
            if (StringUtil.isNotEmpty(getMapping.path())) {
                return getMapping.path()[0];
            }
        }

        PostMapping postMapping = (PostMapping) method.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            if (StringUtil.isNotEmpty(postMapping.value())) {
                return postMapping.value()[0];
            }
            if (StringUtil.isNotEmpty(postMapping.name())) {
                return postMapping.name();
            }
            if (StringUtil.isNotEmpty(postMapping.path())) {
                return postMapping.path()[0];
            }
        }

        PutMapping putMapping = (PutMapping) method.getAnnotation(PutMapping.class);
        if (putMapping != null) {
            if (StringUtil.isNotEmpty(putMapping.value())) {
                return putMapping.value()[0];
            }
            if (StringUtil.isNotEmpty(putMapping.name())) {
                return putMapping.name();
            }
            if (StringUtil.isNotEmpty(putMapping.path())) {
                return putMapping.path()[0];
            }
        }

        DeleteMapping deleteMapping = (DeleteMapping) method.getAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            if (StringUtil.isNotEmpty(deleteMapping.value())) {
                return deleteMapping.value()[0];
            }
            if (StringUtil.isNotEmpty(deleteMapping.name())) {
                return deleteMapping.name();
            }
            if (StringUtil.isNotEmpty(deleteMapping.path())) {
                return deleteMapping.path()[0];
            }
        }
        return null;
    }

    /**
     * 获取请求方式
     * GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
     *
     * @param method 类
     * @return String 请求方式 eg: get 或者 get,put,post 或者 all(所有请求方式)
     * @see RequestMethod
     */
    public static String getRequestMethod(Method method) {
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            RequestMethod[] requestMethods = requestMapping.method();
            StringBuilder methodStr = new StringBuilder();
            if (requestMethods.length > 0) {
                for (RequestMethod requestMethod : requestMethods) {
                    //转小写，并英文逗号拼接
                    methodStr.append(requestMethod.toString()).append(", ");
                }
                return methodStr.substring(0, methodStr.length() - 2);
            }
        }
        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            return RequestMethod.GET.toString();
        }
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            return RequestMethod.POST.toString();
        }
        PutMapping putMapping = method.getAnnotation(PutMapping.class);
        if (putMapping != null) {
            return RequestMethod.PUT.toString();
        }
        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            return RequestMethod.DELETE.toString();
        }
        return "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE";
    }


}
