package chapter3_sederialization;

import org.junit.jupiter.api.Test;

/**
 * @ClassName: TestSeDesialization
 * @Description: 测试序列化
 * @Author: wuwx
 * @Date: 2019-04-11 18:18
 **/
public class TestSeDesialization {
    private final Person p = new Person("wuwx", 1);

    /**
     * 测试java中默认的序列化和反序列化
     */
    @Test
    public void testDefaultInJava() {
        DefaultJavaSerializer defaultJavaSerializer = new DefaultJavaSerializer();
        byte[] se = defaultJavaSerializer.serialize(p);
        Person s = defaultJavaSerializer.deserialize(se,Person.class);
        System.out.println(s);
    }


    private class Person {
        private String name;
        private int age;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

}
