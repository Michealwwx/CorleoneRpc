package chapter5_registercenter;

import chapter5_registercenter.model.ProviderService;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: IRegisterCenter4Provider
 * @Description: 服务端注册中心接口
 * @Author: wuwx
 * @Date: 2019-04-13 11:47
 **/
public interface IRegisterCenter4Provider {
    void registerProvider(final List<ProviderService> serviceMetaData);
    Map<String,List<ProviderService>> getProviderServiceMap();
}
