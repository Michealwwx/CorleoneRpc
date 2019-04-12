package chapter3_sederialization.protobuf;

import chapter3_sederialization.ISerializer;
import com.google.protobuf.GeneratedMessageV3;
import org.apache.commons.beanutils.MethodUtils;

/**
 * @ClassName: ProtobufSerializerUtilByInvoke
 * @Description: 通过java反射抽取一个protobuf对象序列化的通用方法；
 * @Author: wuwx
 * @Date: 2019-04-12 11:09
 **/
public class ProtobufSerializerUtilByInvoke implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        try {
            if(!(obj instanceof GeneratedMessageV3)){
                throw new UnsupportedOperationException("not support obj type");
            }
            return (byte[]) MethodUtils.invokeMethod(obj,"toByteArray",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            if(!GeneratedMessageV3.class.isAssignableFrom(clazz)){
                throw new UnsupportedOperationException("not support obj type");
            }
            Object o = MethodUtils.invokeStaticMethod(clazz,"getDefaultInstance",null);
            return (T) MethodUtils.invokeMethod(o,"parseFrom",new Object[]{data});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
