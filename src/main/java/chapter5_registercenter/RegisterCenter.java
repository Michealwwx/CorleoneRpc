package chapter5_registercenter;

import chapter5_registercenter.model.ProviderService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RegisterCenter
 * @Description: 服务中心实现
 * @Author: wuwx
 * @Date: 2019-04-13 13:36
 **/
public class RegisterCenter implements IRegisterCenter4Provider{
    @Override
    public void registerProvider(List<ProviderService> serviceMetaData) {

    }

    @Override
    public Map<String, List<ProviderService>> getProviderServiceMap() {
        return null;
    }
}
