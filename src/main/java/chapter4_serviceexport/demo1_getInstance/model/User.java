package chapter4_serviceexport.demo1_getInstance.model;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description: 实体类
 * @Author: wuwx
 * @Date: 2019-04-12 15:47
 **/
public class User implements Serializable {
    String name;
    String eamil;

    public User(String name, String eamil) {
        this.name = name;
        this.eamil = eamil;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", eamil='" + eamil + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }
}
