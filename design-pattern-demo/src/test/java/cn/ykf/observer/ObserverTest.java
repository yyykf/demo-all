package cn.ykf.observer;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/10/14
 */
public class ObserverTest {

    @Test
    void testObserver() {
        EnvData envData = new EnvData();
        EnvDisplay observer = new EnvDisplay();

        // 注册观察者
        envData.registerObserver(observer);

        IntStream.range(0, ThreadLocalRandom.current().nextInt(1, 100)).boxed().forEach(num -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            envData.setMeasurement(random.nextDouble(0, 40), random.nextDouble(100));
        });

    }
}
