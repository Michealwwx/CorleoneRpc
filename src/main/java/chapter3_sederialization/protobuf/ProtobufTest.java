package chapter3_sederialization.protobuf;

import chapter3_sederialization.protobuf.protogeneratefile.Student;
import org.junit.Test;

/**
 * @ClassName: ProtobufTest
 * @Description: 测试
 * @Author: wuwx
 * @Date: 2019-04-12 11:16
 **/
public class ProtobufTest {
    @Test
    public void test(){
        Student.Student1 student1 = Student.Student1.newBuilder()
                .setEmail("email")
                .setId(1)
                .setName("yeye")
                .build();
        ProtobufSerializerUtilByInvoke utilByInvoke = new ProtobufSerializerUtilByInvoke();
        byte[] re =utilByInvoke.serialize(student1);
        Student.Student1 result= utilByInvoke.deserialize(re,Student.Student1.class);
        System.out.println(result);
    }


}
