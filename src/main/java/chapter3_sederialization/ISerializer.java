package chapter3_sederialization;

/**
 * @InterfaceName: ISerializer
 * @Description: 序列化/反序列化通用接口
 * @Author: wuwx
 * @Date: 2019-04-11 18:10
 **/
public interface ISerializer {
    /**
     * 序列化
     * @param obj
     * @param <T>
     * @return
     */
    <T> byte[] serialize(T obj);

    /**
     * 反序列化
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] data,Class<T> clazz);

}
