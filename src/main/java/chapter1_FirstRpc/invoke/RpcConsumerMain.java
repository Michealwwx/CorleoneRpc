package chapter1_FirstRpc.invoke;

import chapter1_FirstRpc.framework.ConsumerProxy;
import chapter1_FirstRpc.service.HelloService;

/**
 * @ClassName: RpcConsumerMain
 * @Description: 服务消费者发起调用
 * @Author: wuwx
 * @Date: 2019-04-11 13:25
 **/
public class RpcConsumerMain {
    public static void main(String[] args) throws Exception {
        HelloService service = ConsumerProxy.consume(HelloService.class,"127.0.0.1",8083);
        for(int i=0;i<1000;i++){
            String hello = service.sayHello("wuwx"+i);
            System.out.println(hello);
            Thread.sleep(3000);
        }
    }


}
