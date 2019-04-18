package zookeeper.curator.Delete_Node;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @ClassName: Delete_Node_Sample
 * @Description: 删除节点；
 * @Author: wuwx
 * @Date: 2019-04-17 13:16
 **/
public class Delete_Node_Sample {
    static String path ="/wuwxNode1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();

    /**
     * api介绍；
     * @throws Exception
     */
    public void delete() throws Exception {
        //删除一个节点；
        client.delete().forPath(path);
        //删除一个节点并递归删除其所有子节点；
        client.delete().deletingChildrenIfNeeded().forPath(path);
        //删除一个节点，强制指定版本进行删除；
        int version = 1;
        client.delete().withVersion(version).forPath(path);
        //删除一个节点，强制保证删除；
        //TODO 注意：guaranteed()接口只是一个保障措施，只要客户端会话有效，
        //TODO 那么后台会持续进行删除操作，直到节点删除成功；

        /**
         * 使用场景：客户端执行一个删除操作，由于一些网络原因，导致删除操作失败，
         * 对于这个异常，有些场景中是致命的，例如“Master选举”，在这个场景中，zk客户端通常是
         * 通过节点的创建和删除来实现的。针对这个问题，curator中引入了一种重试机制：
         * 如果我们调用了guaranteed方法，当客户端碰到上面这些异常，会记录下这次失败的删除操作，
         * 只要客户端会话有效，则会在后台反复重试，知道节点删除成功。
         */
        client.delete().guaranteed().forPath(path);

    }

    /**
     * 测试删除节点API；  假设节点为:  /A/B  ,现在要删除A，则也会把儿子干掉；
     * @throws Exception
     */
    @Test
    public void deleteDataSample() throws Exception {
        client.start();
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(path);
        client.delete().deletingChildrenIfNeeded()
                .withVersion(stat.getVersion()).forPath(path);
    }

}
