package cn.ykf.jdk.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 保留到运行时的注解
 *
 * @author YuKaiFan
 * @date 2021/11/30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RuntimeAnnotation {
}
