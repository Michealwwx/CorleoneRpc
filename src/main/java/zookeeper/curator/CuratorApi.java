package zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CuratorApi
 * @Description: API测试
 * @Author: wuwx
 * @Date: 2019-04-17 11:51
 **/
public class CuratorApi {

    /**
     * 测试创建节点；
     * @throws InterruptedException
     */
    public void testCreateNode() throws Exception {
        //1.先创建session
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();

        //2.创建一个节点；初始内容为空
        String path = "";
        client.create().forPath(path);

        //创建一个节点，附带初始内容；
        client.create().forPath(path,"init".getBytes());

        //创建一个临时节点，初始内容为空；
        client.create().withMode(CreateMode.EPHEMERAL).forPath(path);

        //创建一个临时节点，并自动递归创建父节点；
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .forPath(path);


    }

}
