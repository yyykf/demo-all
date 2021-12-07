package cn.ykf.mvc.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 枚举通用类
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/12/7
 */
public interface BaseEnum {

    /**
     * 获取枚举code，默认用于json的序列化/反序列化
     *
     * @return 枚举code
     */
    @JsonValue
    int getCode();

    /**
     * 获取枚举字面量，已经由 {@link Enum#name()} 实现
     *
     * @return 枚举字面量
     */
    String name();
}
