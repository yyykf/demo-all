package cn.ykf.jdk.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 只保留在源码中的注解
 *
 * @author YuKaiFan
 * @date 2021/11/30
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface SourceAnnotation {
}
