package zookeeper.javaapi.auth;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName AuthSample
 * @Description ACL (Access Control) 权限控制
 * @Author wuwenxiang
 * @Date 2019-04-18 22:24
 * @Version 1.0
 **/
public class AuthSample {
    private static String path = "/acl_test";

    /**
     * 创建一个有权限的节点
     * @throws IOException
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void test1() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zookeeper = new ZooKeeper("127.0.0.01:2181",50000,null);
        //添加权限信息； 只针对本次会话；
        zookeeper.addAuthInfo("digest","foo:true".getBytes());
        zookeeper.create(path,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
        Thread.sleep(100000);
    }

    /**
     * 使用无权限信息的zk会话访问含权限信息的数据节点；
     */
    @Test
    public void getNodeByNoAcl() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.01:2181",50000,null);
        zooKeeper.getData(path,false,null);
    }

    /**
     * 有权限的获取数据
     * @throws IOException
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getNodeByAcl() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.01:2181",50000,null);
        zooKeeper.addAuthInfo("digest","foo:true".getBytes());
        zooKeeper.getData(path,false,null);

    }

    /**
     * 使用错误权限访问数据
     * @throws IOException
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getNodeByWrongAcl() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.01:2181",50000,null);
        zooKeeper.addAuthInfo("digest","foo:false".getBytes());
        zooKeeper.getData(path,false,null);

    }

}
