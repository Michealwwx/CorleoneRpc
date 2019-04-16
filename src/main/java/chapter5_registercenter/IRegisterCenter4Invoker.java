package chapter5_registercenter;

import chapter5_registercenter.model.InvokerService;
import chapter5_registercenter.model.ProviderService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IRegisterCenter4Invoker
 * @Description: 消费端注册中心接口
 * @Author: wuwx
 * @Date: 2019-04-13 13:30
 **/
public interface IRegisterCenter4Invoker {
    /**
     * 消费端初始化服务提供者信息本地缓存
     */
    void initProviderMap();

    /**
     * 消费端获取服务提供者信息
     * @return
     */
    Map<String, List<ProviderService>> getServiceMetaDataMap4Consume();

    /**
     * 消费端将消费者信息注册到ZK 相应的节点下；
     * @param service
     */
    void registerInvoker(final InvokerService service);

}
