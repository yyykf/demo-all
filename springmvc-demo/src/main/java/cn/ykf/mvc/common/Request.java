package cn.ykf.mvc.common;

import cn.ykf.mvc.enums.ResultCode;

import java.io.Serializable;

/**
 * 请求对象
 *
 * @author YuKaiFan
 * @date 2021/12/7
 */
public class Request implements Serializable {

    private ResultCode code;

    public ResultCode getCode() {
        return code;
    }

    public Request setCode(ResultCode code) {
        this.code = code;
        return this;
    }
}
