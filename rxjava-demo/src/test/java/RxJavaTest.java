import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.Arrays;

/**
 * RxJava Demo
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/10/28
 * @link <a href="https://factoryhr.medium.com/understanding-java-rxjava-for-beginners-5eacb8de12ca"/>
 */
public class RxJavaTest {

    /** 观察者 */
    private final Subscriber<Object> subscriber = new Subscriber<Object>() {
        @Override
        public void onCompleted() {
            System.out.println("done.");
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(Object    s) {
            System.out.println("Received: " + s);
        }
    };

    @Test
    @DisplayName("简单使用")
    void testSimpleUsage() {
        // 创建被观察者 todo 所以是怎么样持续地向订阅者发送数据的呢？
        Observable<String> myObservable = Observable.create(subscriber -> {
            // 订阅的时候开始传送数据
            subscriber.onNext("data");
            subscriber.onCompleted();
        });

        myObservable.subscribe(subscriber);

    }

    @Test
    @DisplayName("通过Just快捷创建Observable")
    void testJust() {
        // just 只会发布单一数据
        Observable<String> observable = Observable.just("data");

        observable.subscribe(subscriber);
    }

    @Test
    @DisplayName("通过From快捷创建Observable")
    void testFrom() {
        // from 可以从一个数据源中逐个发布数据
        Observable<String> observable = Observable.from(Arrays.asList("data1", "data2", "data3", "data4"));

        observable.subscribe(subscriber);
    }

    @Test
    @DisplayName("数据加工")
    void testOperator() {
        Observable<Integer> observable = Observable.just("data")
                .map(s -> {
                    // 对数据进行加工处理
                    System.out.println("Start Changing Data.");
                    return s + "(changed)";
                })
                // 可以多次加工处理
                .map(String::toUpperCase)
                // 也可以和Stream流一样，进行类型转换
                .map(s -> 1);

        observable.subscribe(subscriber);
    }
}
