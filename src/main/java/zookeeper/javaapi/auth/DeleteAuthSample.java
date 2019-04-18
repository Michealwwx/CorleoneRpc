package zookeeper.javaapi.auth;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName DeleteAuthSample
 * @Description 删除节点；
 *
 * @Author wuwenxiang
 * @Date 2019-04-18 22:33
 * @Version 1.0
 **/
public class DeleteAuthSample {
    private static String path1 = "/acl_delete_test";
    private static String path2 = "/acl_delete_test/child";


    @Test
    public void testDelete() throws IOException, KeeperException, InterruptedException {

        ZooKeeper zookeeper = new ZooKeeper("127.0.0.01:2181", 50000, null);
        //添加权限信息； 只针对本次会话；
        zookeeper.addAuthInfo("digest", "foo:true".getBytes());
        //TODO 注意如果主节点是临时节点的话，则不能去创建子节点；
        zookeeper.create(path1, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zookeeper.create(path2, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);


        //无权限删除；
        ZooKeeper zookeeper1 = new ZooKeeper("127.0.0.01:2181", 50000, null);
        try {
            zookeeper1.delete(path2,-1);
        } catch (Exception e) {
            System.out.println("删除节点失败："+path2+e.getMessage());
        }

        //有权限删除；
        ZooKeeper zookeeper2 = new ZooKeeper("127.0.0.01:2181", 50000, null);
        zookeeper2.addAuthInfo("digest", "foo:true".getBytes());
        zookeeper2.delete(path2,-1);
        System.out.println("成功删除："+path2);

        //TODO 重点关注这次操作；使用的是没有权限的客户端会话，最终却从删除了数据节点；
        //原因：删除节点接口的权限控制比较特殊，当客户端对一个数据节点添加了权限信息后，对于删除操作而言，其操作范围是其子结点；
        //也就是说，当我们对一个数据节点添加权限信息后，依然可以自由删除这个节点，但是对于该节点的子节点，必须使用相应的权限才能删掉；
        ZooKeeper zookeeper3 = new ZooKeeper("127.0.0.01:2181", 50000, null);
        zookeeper3.delete(path1,-1);
        System.out.println("成功删除："+path1);





    }


}
