package zookeeper.javaapi.createNode;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: Zookeeper_Create_API_Sync_Usage
 * @Description: 使用同步api创建一个节点；
 * @Author: wuwx
 * @Date: 2019-04-15 15:57
 **/
public class Zookeeper_Create_API_Sync_Usage implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",
                5000,new Zookeeper_Create_API_Sync_Usage());

        countDownLatch.await();
        String path1 = zooKeeper.create("/zk_test-ephemeral","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("创建临时顺序节点：:"+path1);
        String path2 = zooKeeper.create("/zk_test-ephemeral","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("创建临时顺序节点："+path2);

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        if(Event.KeeperState.SyncConnected == event.getState()){
            countDownLatch.countDown();
        }
    }
}
