package cn.ykf.mvc.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 响应码
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/12/7
 */
public enum ResultCode implements BaseEnum {

    /** OK */
    OK(200, "OK"),
    /** ERROR */
    ERROR(500, "ERROR");

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultCode.class);
    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @JsonCreator
    public static ResultCode create(String name) {
        try {
            // 先尝试用字面量反序列化
            return ResultCode.valueOf(name);
        } catch (IllegalArgumentException ignore) {
            // 字面量找不到的话再尝试用code反序列化
            LOGGER.info("Cannot deserialize ResultCode by name「{}」, try deserialize by code", name);

            int code = Integer.parseInt(name);

            for (ResultCode value : ResultCode.values()) {
                if (value.code == code) {
                    return value;
                }
            }
        }

        throw new IllegalArgumentException(String.format("No Element Matches「%s」.", name));
    }

}
