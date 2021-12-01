package cn.ykf.jdk.anno;

import cn.ykf.jdk.model.WithClassAnnotation;
import cn.ykf.jdk.model.WithRuntimeAnnotation;
import cn.ykf.jdk.model.WithSourceAnnotation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/11/30
 */
public class AnnotationTest {

    @Test
    void testClassAnnotation() {
        assertFalse(WithSourceAnnotation.class.isAnnotationPresent(SourceAnnotation.class));
        assertFalse(WithClassAnnotation.class.isAnnotationPresent(ClassAnnotation.class));
        assertTrue(WithRuntimeAnnotation.class.isAnnotationPresent(RuntimeAnnotation.class));
    }
}
