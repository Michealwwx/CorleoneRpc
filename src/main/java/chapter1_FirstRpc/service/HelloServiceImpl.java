package chapter1_FirstRpc.service;

/**
 * @ClassName: HelloServiceImpl
 * @Description: 远程服务的实现类。
 * @Author: wuwx
 * @Date: 2019-04-11 13:26
 **/
public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String content) {
        return "hello,"+content;
    }
}
