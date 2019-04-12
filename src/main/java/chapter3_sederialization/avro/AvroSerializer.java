package chapter3_sederialization.avro;

import chapter3_sederialization.ISerializer;

/**
 * @ClassName: AvroSerializer
 * @Description: Avro序列化；
 * @Author: wuwx
 * @Date: 2019-04-12 13:54
 **/
public class AvroSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return null;
    }
}
