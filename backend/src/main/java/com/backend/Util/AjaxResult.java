package com.backend.Util;

import java.util.HashMap;

public class AjaxResult extends HashMap<String, Object>
{
    public static final String STATUS_TAG = "status";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";

    /**
     * status code
     */
    public enum Type
    {
        SUCCESS(0),
        WARN(400),
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

    /**
     * Empty AjaxResult.
     */
    public AjaxResult()
    {
    }

    /**
     * @param type status
     * @param msg return message
     */
    public AjaxResult(Type type, String msg)
    {
        super.put(STATUS_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * @param type status
     * @param msg return message
     * @param data data object
     */
    public AjaxResult(Type type, String msg, Object data)
    {
        super.put(STATUS_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (data != null)
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * @return message for success
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("success",null);
    }

    /**
     * @param msg specific return message
     * @return message for success
     */
    public static AjaxResult success(String msg) { return AjaxResult.success(msg, null); }

    /**
     * @return message for success
     */
    public static AjaxResult success(Object data)
    {
        return AjaxResult.success("success", data);
    }

    /**
     * @param msg
     * @param data
     * @return message for success
     */
    public static AjaxResult success(String msg, Object data)
    {
        return new AjaxResult(Type.SUCCESS, msg, data);
    }

    /**
     * @param msg specific return message
     * @return message for warning
     */
    public static AjaxResult warn(String msg)
    {
        return AjaxResult.warn(msg, null);
    }

    /**
     * @param msg
     * @param data
     * @return message for warning
     */
    public static AjaxResult warn(String msg, Object data)
    {
        return new AjaxResult(Type.WARN, msg, data);
    }

    /**
     * @return error
     */
    public static AjaxResult error()
    {
        return AjaxResult.error("error", null);
    }

    /**
     * @param msg
     * @return message for error
     */
    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(msg, null);
    }

    /**
     * @param msg
     * @param data
     * @return message for error
     */
    public static AjaxResult error(String msg, Object data)
    {
        return new AjaxResult(Type.ERROR, msg, data);
    }
}

