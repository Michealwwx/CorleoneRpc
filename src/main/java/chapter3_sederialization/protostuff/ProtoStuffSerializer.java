package chapter3_sederialization.protostuff;

import chapter3_sederialization.ISerializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ProtoStuffSerializer
 * @Description: protostuff序列化方式;对比protobuf优势在于无需编写proto文件，运行时动态生成代码；
 * @Author: wuwx
 * @Date: 2019-04-12 11:21
 **/
public class ProtoStuffSerializer implements ISerializer {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>,Schema<?>>();
    private static Objenesis objenesis = new ObjenesisStd(true);

    private static <T> Schema<T> getSchema(Class<T> cls){
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if(schema==null){
            schema = RuntimeSchema.createFrom(cls);
            cachedSchema.put(cls,schema);
        }
        return schema;
    }

    @Override
    public <T> byte[] serialize(T obj) {

        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer=LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
              Schema<T> schema =getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj,schema,buffer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            buffer.clear();
        }
        return null;

    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        T message = objenesis.newInstance(clazz);
        Schema<T> schema = getSchema(clazz);
        ProtostuffIOUtil.mergeFrom(data,message,schema);
        return message;
    }
}
