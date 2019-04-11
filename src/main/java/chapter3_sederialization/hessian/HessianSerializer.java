package chapter3_sederialization.hessian;

import chapter3_sederialization.ISerializer;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * hessian序列化要点：要序列化的对象必须实现 Serializer接口；
 */
public class HessianSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            HessianOutput ho = new HessianOutput(outputStream);
            ho.writeObject(obj);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        if (data == null) {
            throw new NullPointerException();
        }
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            HessianInput hi = new HessianInput(inputStream);
            return (T) hi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
