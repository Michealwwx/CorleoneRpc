package zookeeper.curator.Create_Node;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Curator_CreateNode_Sample
 * @Description: 创建节点；
 * @Author: wuwx
 * @Date: 2019-04-17 11:58
 **/
public class Curator_CreateNode_Sample {
    static String path ="/wuwxNode1/erzi";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();


    @Test
    public void testCreateNode() throws Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path,"1995-10".getBytes());
        TimeUnit.SECONDS.sleep(2000);
    }

}
