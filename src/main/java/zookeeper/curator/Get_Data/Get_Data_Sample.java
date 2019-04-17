package zookeeper.curator.Get_Data;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @ClassName Get_Data_Sample
 * @Description 使用Curator获取数据；
 * @Author wuwenxiang
 * @Date 2019-04-17 20:50
 * @Version 1.0
 **/
public class Get_Data_Sample {
    static String path = "/getData";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    @Test
    public void getData() throws Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(path,"init".getBytes());
        Stat stat = new Stat();
        System.out.println(client.getData().storingStatIn(stat).forPath(path));
    }


}
