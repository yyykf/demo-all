import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.Single;
import rx.Subscriber;
import rx.subjects.ReplaySubject;

import java.io.Serializable;
import java.util.Arrays;

/**
 * RxJava Demo
 *
 * @author YuKaiFan
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
        public void onNext(Object s) {
            System.out.println("Received: " + s);
        }
    };

    @Test
    @DisplayName("简单使用")
    void testSimpleUsage() {
        // 创建被观察者 todo hystrix 怎么通过该机制来实现滑动时间窗口的？
        Observable<String> myObservable = Observable.create(subscriber -> {
            for (int i = 0; i < 50; i++) {
                // 当有订阅时会阻塞当前线程
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("data" + i);
                }
            }

            if (!subscriber.isUnsubscribed()) {
                subscriber.onCompleted();
            }
        });

        myObservable.subscribe(subscriber);

        System.out.println("Everything is done.");
    }

    @Test
    void testNonBlocking() {
        // 创建被观察者 todo hystrix 怎么通过该机制来实现滑动时间窗口的？
        Observable<String> myObservable = Observable.create(subscriber -> {
            new Thread(() -> {
                for (int i = 0; i < 50; i++) {
                    // 当有订阅时会阻塞当前线程
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext("data" + i);
                    }
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }).start();
        });

        myObservable.subscribe(subscriber);

        System.out.println("Everything is done.");
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

    @Test
    void testSingle() {
        Single.just("emmit once").map(s -> s + ".").subscribe(subscriber);

        Single.error(new RuntimeException()).subscribe(subscriber);
    }

    @Test
    void testSubject() {
        Observable<Serializable> observable = Observable.from(Arrays.asList("1", new RuntimeException(), "2", "3"));

        ReplaySubject<Object> subject = ReplaySubject.create(5);

        observable.subscribe(subject);

        subject.subscribe(subscriber);
    }
}
