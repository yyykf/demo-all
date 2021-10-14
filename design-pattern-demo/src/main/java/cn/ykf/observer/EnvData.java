package cn.ykf.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 环境主体，被观察者
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/10/14
 */
public class EnvData implements Subject {

    /** 观察者集合 */
    private final List<Observer> observers = new ArrayList<>();
    /** 温度 */
    private double temperature;
    /** 湿度 */
    private double humidity;

    @Override
    public void registerObserver(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(this.temperature, this.humidity));
    }

    /**
     * 设置环境测量结果
     *
     * @param temperature 新的温度
     * @param humidity    新的湿度
     */
    public void setMeasurement(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.measurementChanged();
    }

    public void measurementChanged() {
        // 测量数据发生变化，通知观察者
        this.notifyObservers();
    }
}
