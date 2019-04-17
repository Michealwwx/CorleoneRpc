package zookeeper.curator.Distributed_Lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName DistributeLock
 * @Description 使用curator实现分布式锁；
 * @Author wuwenxiang
 * @Date 2019-04-17 22:39
 * @Version 1.0
 **/
public class DistributeLock {
    static String path = "/distribute_lock";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .sessionTimeoutMs(5000)
            .build();

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 无锁状态；
     */
    @Test
    public void testNoLock() {
        client.start();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat format = new SimpleDateFormat("hh-MM-ss |SSS");
                String orderNo = format.format(new Date());
                System.out.println("生成的订单号为：" + orderNo);

            }).start();
        }
        countDownLatch.countDown();
    }

    /**
     * 添加分布式锁； TODO 问题：释放了未获取到的锁；
     */
    @Test
    public void testDistributeLock() {
        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client, path);
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                try {
                    countDownLatch.await();
                    //TODO 应该也是AQS的实现；
                    lock.acquire();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        }
        SimpleDateFormat format = new SimpleDateFormat("hh-MM-ss |SSS");
        String orderNo = format.format(new Date());
        System.out.println("生成的订单号为：" + orderNo);
        try {
            lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }
}
