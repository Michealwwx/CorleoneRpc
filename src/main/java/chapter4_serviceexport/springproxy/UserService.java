package chapter4_serviceexport.springproxy;

import chapter4_serviceexport.demo1_getInstance.model.User;

/**
 * @InterfaceName: UserService
 * @Description: TODO
 * @Author: wuwx
 * @Date: 2019-04-12 16:17
 **/
public interface UserService {
    User findByName(String userName);
}
