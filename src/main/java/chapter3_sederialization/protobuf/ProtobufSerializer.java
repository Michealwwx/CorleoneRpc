package chapter3_sederialization.protobuf;

import chapter3_sederialization.protobuf.protogeneratefile.Student;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName: ProtobufSerializer
 * @Description: protobuf序列化方式；
 * @Author: wuwx
 * @Date: 2019-04-12 09:50
 **/
public class ProtobufSerializer {
    @Test
    public void userProtobufInJava() throws InvalidProtocolBufferException {
        Student.Student1 student1 = Student.Student1.newBuilder()
                .setName("wuwx")
                .setId(1)
                .setEmail("280971337@qq.com")
                .build();
        //序列化方法一：
        System.out.println("序列化(toByteString):"+student1.toByteString());
        //序列化方法二：
        System.out.println("序列化(toByteArray)："+Arrays.toString(student1.toByteArray()));
        //反序列化方法一：
        Student.Student1 de = Student.Student1.parseFrom(student1.toByteString());
        System.out.println("反序列化:"+de);
        //反序列化方法二:
        Student.Student1 de1 = Student.Student1.parseFrom(student1.toByteArray());
        System.out.println("反序列化"+de1);


    }

}
