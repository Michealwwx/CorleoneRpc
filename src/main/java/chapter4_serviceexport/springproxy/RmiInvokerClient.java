package chapter4_serviceexport.springproxy;

import chapter4_serviceexport.demo1_getInstance.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: RmiInvokerClient
 * @Description: TODO
 * @Author: wuwx
 * @Date: 2019-04-12 16:56
 **/
public class RmiInvokerClient {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter4/rmi/rmi-rpc-client.xml");
        UserService userService = (UserService) context.getBean("userRmiServiceProxy");
        User user = userService.findByName("小王");
        System.out.println(user);
    }
}
