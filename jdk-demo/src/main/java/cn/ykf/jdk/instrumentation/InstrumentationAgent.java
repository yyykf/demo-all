package cn.ykf.jdk.instrumentation;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * <a herf="https://www.devinline.com/2016/05/java-instrumentation-fundamental-part-1.html?m=1">Java instrumentation fundamentals(Part 1): Find size of Object using instrumentation</a>
 *
 * @author YuKaiFan
 * @date 2022/6/7
 */
public class InstrumentationAgent {

    /**
     * System classloader after loading the Agent Class, invokes the premain
     * (premain is roughly equal to main method for normal Java classes)
     */
    private static volatile Instrumentation instrumentation;

    /**
     * <code>
     * public static void premain(String agentArgs, Instrumentation instObj){ }
     * </code>
     * <p>
     * <code>
     * public static void premain(String agentArgs){ }
     * </code>
     * <p>
     * an Instrumentation instance is passed to the premain method of the agent class.
     *
     * @param agentArgs
     * @param instObj
     */
    public static void premain(String agentArgs, Instrumentation instObj) {
        // instObj is handle passed by JVM
        instrumentation = instObj;
    }

    public static void agentmain(String agentArgs, Instrumentation instObj)
            throws ClassNotFoundException, UnmodifiableClassException {
    }

    public static long findSizeOfObject(Object obj) {
        // use instrumentation to find size of object obj
        if (instrumentation == null) {
            throw new IllegalStateException("Agent not initialised");
        } else {
            return instrumentation.getObjectSize(obj);
        }
    }
}
