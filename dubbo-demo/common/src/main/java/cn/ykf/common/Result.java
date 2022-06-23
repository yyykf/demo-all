package cn.ykf.common;

import java.io.Serializable;

/**
 * @author YuKaiFan
 * @date 2022/6/23
 */
public class Result<T> implements Serializable {

    /** 结果码 */
    private int retCode;
    /** 提示信息 */
    private String msg;
    /** 额外附带的信息 */
    private String extra;
    /** 结果集 */
    private T result;

    private Result() {
    }

    private Result(int retCode, String msg) {
        this(retCode, msg, null, null);
    }

    private Result(int retCode, String msg, T result, String extra) {
        this.retCode = retCode;
        this.msg = msg;
        this.extra = extra;
        this.result = result;
    }

    public int getRetCode() {
        return retCode;
    }

    public String getMsg() {
        return msg;
    }

    public String getExtra() {
        return extra;
    }

    public T getResult() {
        return this.result;
    }

    /**
     * 是否为成功的结果
     *
     * @return
     */
    public boolean isSucc() {
        return 200 == retCode;
    }

    public static <E> Result<E> succ(E result) {
        return succ(result, null);
    }

    public static Result<String> succ(String msg) {
        return new Result<>(200, msg);
    }

    public static <E> Result<E> succ(E result, String msg) {
        return new Result<E>(200, msg, result, null);
    }

    public static Result fail(String msg) {
        return fail(-1, msg);
    }

    public static Result fail(int retCode, String msg) {
        return new Result<String>(retCode, msg, null, null);
    }

    @Override
    public String toString() {
        return "{\"retCode\":\"" + retCode + "\",\"msg\":\"" + msg + "\",\"succ\":" + isSucc() + ",\"extra\":\"" + extra
                + "\"}";
    }
}
