package zookeeper.curator.Create_Session;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Create_Session_Sample_fluent
 * @Description: 使用curator的流式分格创建会话；
 * @Author: wuwx
 * @Date: 2019-04-17 11:37
 **/
public class Create_Session_Sample_fluent {

    /**
     * 使用流式风格创建会话
     * @throws InterruptedException
     */
    @Test
    public void createSession() throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    /**
     * 创建含隔离命名空间的会话；
     * TODO:待深入
     */
    @Test
    public void createNameSpaceSession(){
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .namespace("base")
                .build();
    }


}
