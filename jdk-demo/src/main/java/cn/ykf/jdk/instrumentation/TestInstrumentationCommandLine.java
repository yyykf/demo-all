package cn.ykf.jdk.instrumentation;

/**
 * java -javaagent:agent.jar
 *
 * @author YuKaiFan
 * @date 2022/6/7
 */
public class TestInstrumentationCommandLine {

    public static void main(String[] args) {
        long sizeOfObject = InstrumentationAgent.findSizeOfObject(new Object());
        System.out.println("Size of new object is " + sizeOfObject);

        sizeOfObject = InstrumentationAgent.findSizeOfObject(new SampleClass(12, "Hello", 2345L));
        System.out.println("Size of new sample class object is " + sizeOfObject);
    }

    static class SampleClass {
        /** 4 bytes */
        int number;
        /** 12 bytes (char value[]; 4 bytes int offset; // 4 bytes int count; // 4 bytes) */
        String name;
        /** 8 bytes */
        Long ssn;

        public SampleClass() {
        }

        public SampleClass(int number, String name, Long ssn) {
            super();
            this.number = number;
            this.name = name;
            this.ssn = ssn;
        }
    }
}
