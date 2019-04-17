package zookeeper.curator.Call_Back;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TestBackGroundCallBack
 * @Description curator提供的异步操作；
 * @Author wuwenxiang
 * @Date 2019-04-17 21:09
 * @Version 1.0
 **/
public class TestBackGroundCallBack {
    static String path = "/asyn_method";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();
    static CountDownLatch countDownLatch = new CountDownLatch(2);
    static ExecutorService es = Executors.newFixedThreadPool(2);

    /**
     * 测试异步接口；TODO 注意：如果传入了线程池，那么异步事件处理逻辑都会交由线程池去做；
     */
    @Test
    public void testAsyn1() throws Exception {
        client.start();
        System.out.println("主线程："+Thread.currentThread().getName());

        //1.传入自定义的线程池；
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("有线程池：event[code:" + event.getResultCode() + ",type:" + event.getType() + "]");
                        System.out.println("Thread of processResult:" + Thread.currentThread().getName());
                        countDownLatch.countDown();
                    }
                    //此处传入了线程池用于专门用来处理比较复杂的时间；因为zk中所有异步通知事件都是由EventThread这个线程来处理的
                    //这个线程是“串行处理机制”，虽然能保证事件处理的顺序性，但是如果遇到复杂的处理单元，会消耗过长的处理时间，会影响其他事件的处理。
                }, es).forPath(path, "hasExecutorService".getBytes());

        //2.不传入自定义的线程池；
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .inBackground((CuratorFramework framework, CuratorEvent event) -> {
                    System.out.println("无线程池：event[code:" + event.getResultCode() + ",type：" + event.getType() + "]");
                    System.out.println("Thread of processResult:" + Thread.currentThread().getName());
                    countDownLatch.countDown();
                })
                .forPath(path, "noExcutorService".getBytes());
        countDownLatch.await();
        es.shutdown();
    }
}
