package chapter4_serviceexport;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.objenesis.Objenesis;

/**
 * @ClassName: ProviderFactoryBean
 * @Description: 实现远程服务的发布
 * @Author: wuwx
 * @Date: 2019-04-12 17:52
 **/
public class ProviderFactoryBean implements FactoryBean, InitializingBean {
    //服务接口
    private Class<?> serviceItf;
    //服务实现
    private Object serviceObject;
    //服务端口
    private String serverPort;
    //服务超时时间
    private long timeout;
    //服务代理对象，暂时没有用到；
    private Object serviceProxyObject;
    //服务提供者唯一标识
    private String appKey;
    //服务分组组名
    private String groupName="default";
    //服务提供者权重，默认为1，范围为 1-100
    private int weight=1;
    //服务端线程数，默认10个线程；
    private int workerThreads = 10;
    @Override
    public Object getObject() throws Exception {
        return serviceProxyObject;
    }

    @Override
    public Class<?> getObjectType() {
        return serviceItf;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * spring bean在初始化时自动执行；
     * 在这里主要做两件事情：
     * 1.调用NettyServer.singleton().start()方法来启动netty服务端，将服务对外发布出去，
     *   使其能够接收其他机器的调用请求；
     * 2.将服务消息写入zookeeper，保存在服务注册中心。
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //启动netty服务端；
        //NettyServer.singleton().start(Integer.parseInt(serverPort));
        //注册到zookeeper，元数据注册中心；TODO
        //List<ProviderService> providerServiceList = null;
        //IRegisterCenter4Provider registerCenter4Provider = RegisterCenter.singleton();
    }
}
