package chapter4_serviceexport.demo1_getInstance.main;

import chapter4_serviceexport.demo1_getInstance.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: MainClient
 * @Description: TODO
 * @Author: wuwx
 * @Date: 2019-04-12 16:27
 **/
public class MainClient {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter4.demo1/spring.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
    }
}
