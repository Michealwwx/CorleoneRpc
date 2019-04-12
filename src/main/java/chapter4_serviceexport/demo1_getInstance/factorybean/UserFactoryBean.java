package chapter4_serviceexport.demo1_getInstance.factorybean;

import chapter4_serviceexport.demo1_getInstance.model.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName: UserFactoryBean
 * @Description: TODO
 * @Author: wuwx
 * @Date: 2019-04-12 15:43
 **/
public class UserFactoryBean implements FactoryBean<User> {


    public static final User user =new User();

    private String name;
    private String email;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public User getObject() throws Exception {
        user.setEamil(email);
        user.setName(name);
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
