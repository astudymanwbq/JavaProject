package com.wbq.util;

/**
 * @Author wubiqin
 * @Date 2017-12-10 13:46
 * @Description
 */
public class ActionResult<T> {
    private boolean success;//状态
    private T data;//返回的数据
    private String msg;//成功或错误的信息

    @Override
    public String toString() {
        return "ActionResult{" +
                "success=" + success +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
