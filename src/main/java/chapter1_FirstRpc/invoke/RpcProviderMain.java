package chapter1_FirstRpc.invoke;

import chapter1_FirstRpc.framework.ProviderReflect;
import chapter1_FirstRpc.service.HelloService;
import chapter1_FirstRpc.service.HelloServiceImpl;

/**
 * @ClassName: RpcProviderMain
 * @Description: 发布服务，以便接收调用
 * @Author: wuwx
 * @Date: 2019-04-11 13:25
 **/
public class RpcProviderMain {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service,8083);
    }

}
