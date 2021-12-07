package cn.ykf.observer;

/**
 * 环境数据展示器，观察者
 *
 * @author YuKaiFan
 * @date 2021/10/14
 */
public class EnvDisplay implements Observer {

    /** 温度 */
    private double temperature;
    /** 湿度 */
    private double humidity;

    @Override
    public void update(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.printf("环境数据发生变化，当前温度: %.2f摄氏度，当前湿度: %.2f%%.%n", temperature, humidity);
    }
}
