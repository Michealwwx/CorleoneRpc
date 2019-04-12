package chapter3_sederialization.thrift;

import chapter3_sederialization.ISerializer;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;

/**
 * @ClassName: ThriftSerializer
 * @Description: Thrift序列化
 * @Author: wuwx
 * @Date: 2019-04-12 13:49
 **/
public class ThriftSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());
        try {
            return serializer.serialize((TBase) obj);
        } catch (TException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            TBase o =(TBase) clazz.newInstance();
            TDeserializer deserializer = new TDeserializer(new TBinaryProtocol.Factory());
            deserializer.deserialize(o,data);
            return (T)o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
        return null;
    }
}
