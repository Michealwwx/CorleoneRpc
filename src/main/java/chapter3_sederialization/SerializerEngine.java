package chapter3_sederialization;


import chapter3_sederialization.defaultInJava.DefaultJavaSerializer;
import chapter3_sederialization.fastjson.FastJsonSerializer;
import chapter3_sederialization.hessian.HessianSerializer;
import chapter3_sederialization.jackson.JacksonSerializer;
import chapter3_sederialization.protobuf.ProtobufSerializerUtilByInvoke;
import chapter3_sederialization.protostuff.ProtoStuffSerializer;
import chapter3_sederialization.thrift.ThriftSerializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: SerializerEngine
 * @Description: 序列化引擎
 * @Author: wuwx
 * @Date: 2019-04-12 14:03
 **/
public class SerializerEngine{
    public static final Map<SerializerType,ISerializer> serializerMap = new ConcurrentHashMap<>();
    //初始化序列化引擎；
    static{
        serializerMap.put(SerializerType.DefaultJavaSerializer,new DefaultJavaSerializer());
        serializerMap.put(SerializerType.FastJsonSerializer,new FastJsonSerializer());
        serializerMap.put(SerializerType.HessianSerialzer,new HessianSerializer());
        serializerMap.put(SerializerType.ProtobufSerializer,new ProtobufSerializerUtilByInvoke());
        serializerMap.put(SerializerType.ProtostuffSerializer,new ProtoStuffSerializer());
        serializerMap.put(SerializerType.ThriftSerializer,new ThriftSerializer());
        serializerMap.put(SerializerType.JscksonSerializer,new JacksonSerializer());
    }


    /**
     * 序列化，并且支持选择序列化的协议；
     * @param obj
     * @param type
     * @param <T>
     * @return
     */
    public <T> byte[] serialize(T obj,String type) {
        SerializerType serializerType = SerializerType.getEnumByType(type);
        if(serializerType == null){
            return null;
        }
        ISerializer serializer = serializerMap.get(serializerType);
        return serializer.serialize(obj);
    }


    public <T> T deserialize(byte[] data, Class<T> clazz,String type) {
        SerializerType serializerType = SerializerType.getEnumByType(type);
        if(serializerType == null){
            return null;
        }
        ISerializer serializer = serializerMap.get(serializerType);
        return serializer.deserialize(data,clazz);
    }
}
