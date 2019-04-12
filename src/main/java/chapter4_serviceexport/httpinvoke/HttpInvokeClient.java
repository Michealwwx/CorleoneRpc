package chapter4_serviceexport.httpinvoke;

import chapter4_serviceexport.demo1_getInstance.model.User;
import chapter4_serviceexport.springproxy.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: HttpInvokeClient
 * @Description: TODO
 * @Author: wuwx
 * @Date: 2019-04-12 17:28
 **/
public class HttpInvokeClient {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter4.httpInvokerproxy/httpinvoker-rpc-client.xml");
        UserService service = (UserService) context.getBean("userServiceProxy");
        User user = service.findByName("小王");
        System.out.println(user);
    }
}
