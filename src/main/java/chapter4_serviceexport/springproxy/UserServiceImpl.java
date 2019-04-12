package chapter4_serviceexport.springproxy;

import chapter4_serviceexport.demo1_getInstance.model.User;
import io.protostuff.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: wuwx
 * @Date: 2019-04-12 16:17
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Map<String,User> userMap=new HashMap<>();

    static {
        userMap.put("小王",new User("小王","xiaowang@qq.com"));
        userMap.put("老王",new User("老王","laowang@qq.com"));
    }

    @Override
    public User findByName(String userName) {
        return userMap.get(userName);
    }
}
