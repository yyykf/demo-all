package cn.ykf.mvc.convert;

import cn.ykf.mvc.enums.BaseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 枚举转换器工厂
 *
 * @author YuKaiFan
 * @date 2021/12/7
 */
public class CodeToEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    /** 枚举转换器集合 */
    private static final ConcurrentMap<Class<? extends BaseEnum>, Converter<String, ? extends BaseEnum>> CONVERTER_MAP =
            new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BaseEnum> Converter<String, T> getConverter(final Class<T> targetType) {

        return (Converter<String, T>) CONVERTER_MAP.computeIfAbsent(targetType,
                baseEnumClass -> new StringToBaseEnum<>(targetType));
    }

    /**
     * String和BaseEnum转换器
     *
     * @param <T> 枚举类型
     */
    private static class StringToBaseEnum<T extends BaseEnum> implements Converter<String, T> {

        private static final Logger LOGGER = LoggerFactory.getLogger(StringToBaseEnum.class);

        /** 管理code/字面量和对应枚举的映射 */
        private final Map<String, T> enumMap = new HashMap<>();

        private StringToBaseEnum(Class<T> enumType) {
            T[] constants = enumType.getEnumConstants();

            for (T constant : constants) {
                int code = constant.getCode();
                String name = constant.name();

                // 枚举code和枚举的映射
                enumMap.put(String.valueOf(code), constant);
                // 枚举name和枚举的映射
                enumMap.put(name, constant);

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Init BaseEnumConverter 「{} / {} -> {}」", code, name, constant);
                }
            }
        }

        @Override
        public T convert(String source) {
            // 只能是code或者字面量
            T t = enumMap.get(source);

            if (t == null) {
                throw new IllegalArgumentException(String.format("No Element Matches「%s」.", source));
            }

            return t;
        }
    }
}
