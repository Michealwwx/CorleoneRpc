package chapter3_sederialization.jackson;

import chapter3_sederialization.ISerializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Date;


/**
 * @ClassName JacksonSerializer
 * @Description 测试Jackson序列化
 * @Author wuwenxiang
 * @Date 2019-04-11 21:04
 * @Version 1.0
 **/
public class JacksonSerializer implements ISerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS,true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);

        SimpleModule module = new SimpleModule("DateTimeModule", Version.unknownVersion());
        module.addDeserializer(Date.class,new FDateJsonDeserializer());
        module.addSerializer(Date.class,new FDateJsonSerializer());
        objectMapper.registerModule(module);
    }

    private static ObjectMapper getObjectMapperInstance(){
        return objectMapper;
    }
    @Override
    public <T> byte[] serialize(T obj) {
        if(obj == null){
            return new byte[0];
        }
        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
            return json.getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        String json = new String(data);
        try {
            return objectMapper.readValue(json,clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
