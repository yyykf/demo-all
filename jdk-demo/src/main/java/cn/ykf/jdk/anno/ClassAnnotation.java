package cn.ykf.jdk.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 只保留在 Class 中的注解
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/11/30
 */
@Target(ElementType.TYPE)
public @interface ClassAnnotation {
    // 不使用 @Retention 默认就是 CLASS
}
