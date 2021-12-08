package cn.ykf.mvc.config;

import cn.ykf.mvc.convert.CodeToEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 枚举转换器配置
 *
 * @author YuKaiFan
 * @date 2021/12/7
 */
@Configuration
public class EnumWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new CodeToEnumConverterFactory());
    }
}
