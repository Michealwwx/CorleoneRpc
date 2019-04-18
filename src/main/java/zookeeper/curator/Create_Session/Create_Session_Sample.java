package zookeeper.curator.Create_Session;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Create_Session_Sample
 * @Description: 使用Curator创建会话；
 * @Author: wuwx
 * @Date: 2019-04-17 09:39
 **/
public class Create_Session_Sample {

    /**
     * 测试创建会话
     */
    @Test
    public void testCreate() throws InterruptedException {
        /**
         * 重试策略，默认由四种实现，分别是
         * 1.Exponential BackOffRetry,
         * 2.RetryNTimes,
         * 3.RetryOneTime,
         * 4.RetryUtilElapsed
         */
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);

        /**
         * sessionTimeoutMs:会话超时时间，默认 60000ms；
         * connectionTimeoutMs:连接创建超时时间，默认15000ms；
         */
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",5000,3000,retryPolicy);;
        client.start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

    }


}
