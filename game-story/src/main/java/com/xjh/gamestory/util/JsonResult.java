package com.xjh.gamestory.util;


import java.io.Serializable;
import java.util.HashMap;

/**
 * 响应结果类
 * @author 123
 */
public class JsonResult extends HashMap<String,Object> implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "message";

    /** 数据对象 */
    public static final String DATA_TAG = "data";


    /**
     * 状态类型
     */
    public enum Type
    {
        /** 成功 */
        SUCCESS(200),
        /** 警告 */
        WARN(301),
        /**失败 */
        FAIL(400),
        /** 错误 */
        ERROR(500);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    public JsonResult() {
        super();
    }

    /**
     * 初始化一个新创建的 JsonResult 对象
     *
     * @param type 状态类型
     * @param message 返回内容
     */
    public JsonResult(Type type, String message)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, message);
    }

    /**
     * 初始化一个新创建的 JsonResult 对象
     *
     * @param type 状态类型
     * @param message 返回内容
     * @param data 数据对象
     */
    public JsonResult(Type type, String message, Object data)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, message);
        if (com.xjh.gamestory.util.StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 方便链式调用
     *
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public JsonResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static JsonResult success()
    {
        return JsonResult.success("操作成功");
    }


    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static JsonResult success(Object data)
    {
        return JsonResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static JsonResult success(String msg)
    {
        return JsonResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param message 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static JsonResult success(String message, Object data)
    {
        return new JsonResult(Type.SUCCESS, message, data);
    }


    /**
     * 返回警告消息
     *
     * @param message 返回内容
     * @return 警告消息
     */
    public static JsonResult warn(String message)
    {
        return JsonResult.warn(message, null);
    }

    /**
     * 返回警告消息
     *
     * @param message 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static JsonResult warn(String message, Object data)
    {
        return new JsonResult(Type.WARN, message, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static JsonResult error()
    {
        return JsonResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param message 返回内容
     * @return 警告消息
     */
    public static JsonResult error(String message)
    {
        return JsonResult.error(message, null);
    }

    /**
     * 返回错误消息
     *
     * @param message 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static JsonResult error(String message, Object data)
    {
        return new JsonResult(Type.ERROR, message, data);
    }


    /**
     * 返回失败消息
     *
     * @return 失败消息
     */
    public static JsonResult failed()
    {
        return JsonResult.failed("操作失败");
    }


    /**
     * 返回失败消息
     *
     * @return 失败消息
     */
    public static JsonResult failed(Object data)
    {
        return JsonResult.failed("操作失败", data);
    }

    /**
     * 返回失败消息
     *
     * @param message 返回内容
     * @return 失败消息
     */
    public static JsonResult failed(String message)
    {
        return JsonResult.failed(message, null);
    }

    /**
     * 返回失败消息
     *
     * @param message 返回内容
     * @param data 数据对象
     * @return 失败消息
     */
    public static JsonResult failed(String message, Object data)
    {
        return new JsonResult(Type.FAIL, message, data);
    }

}
