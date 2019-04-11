package chapter3_sederialization;


import chapter3_sederialization.defaultInJava.DefaultJavaSerializer;
import chapter3_sederialization.defaultInJava.Person;
import chapter3_sederialization.fastjson.FastJsonSerializer;
import chapter3_sederialization.hessian.HessianSerializer;
import chapter3_sederialization.jackson.JacksonSerializer;
import chapter3_sederialization.jackson.Order;
import org.junit.Test;

import java.util.Date;

/**
 * @ClassName: TestSeDesialization
 * @Description: 测试序列化
 * @Author: wuwx
 * @Date: 2019-04-11 18:18
 **/
public class TestSeDesialization {
    private final Person p = new Person("wuwx", 1,"上海");
    private final Order order =new Order("wuwx",new Date(),8.87);

    /**
     * 测试java中默认的序列化和反序列化
     */
    @Test
    public void testDefaultInJava() {
        long start = System.currentTimeMillis();
        DefaultJavaSerializer defaultJavaSerializer = new DefaultJavaSerializer();
        byte[] se = defaultJavaSerializer.serialize(p);
        Person s = defaultJavaSerializer.deserialize(se,Person.class);
        System.out.println("序列化+反序列化时间："+(System.currentTimeMillis() -start));
        System.out.println(s);
    }

    /**
     * 测试jackson序列化与反序列化；
     */
    @Test
    public void testJacksonSerializer(){
        long start = System.currentTimeMillis();
        HessianSerializer serializer = new HessianSerializer();
        byte[] array = serializer.serialize(order);
        Order order = serializer.deserialize(array,Order.class);
        System.out.println("序列化+反序列化时间："+(System.currentTimeMillis() -start));
        System.out.println(order);
    }
    @Test
    public void testFastJson(){
        long start = System.currentTimeMillis();
        FastJsonSerializer serializer = new FastJsonSerializer();
        byte[] array = serializer.serialize(order);
        Order order = serializer.deserialize(array,Order.class);
        System.out.println("序列化+反序列化时间："+(System.currentTimeMillis() -start));
        System.out.println(order);
    }
    /**
     * 测试Hessian序列化；
     */
    @Test
    public void testHessianSerializer(){
        long start = System.currentTimeMillis();
        JacksonSerializer defaultJavaSerializer = new JacksonSerializer();
        byte[] se = defaultJavaSerializer.serialize(p);
        Person s = defaultJavaSerializer.deserialize(se,Person.class);
        System.out.println("序列化+反序列化时间："+(System.currentTimeMillis() -start));
        System.out.println(s);
    }




}
