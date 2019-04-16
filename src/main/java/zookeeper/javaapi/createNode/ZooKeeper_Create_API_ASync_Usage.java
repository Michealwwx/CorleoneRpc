package zookeeper.javaapi.createNode;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: ZooKeeper_Create_API_ASync_Usage
 * @Description: 使用异步api 创建一个节点；
 * @Author: wuwx
 * @Date: 2019-04-15 16:42
 **/
public class ZooKeeper_Create_API_ASync_Usage implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,
                new ZooKeeper_Create_API_ASync_Usage());

        countDownLatch.await();

        zooKeeper.create("/zk-test","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new IStringCallBack(),"I am context.");

        zooKeeper.create("/zk-test","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new IStringCallBack(),"I am context.");

        zooKeeper.create("/zk-test","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallBack(),"I am context.");

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }


    static class IStringCallBack implements AsyncCallback.StringCallback{

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("创建节点结果："+rc+","+path+","+ctx+","+"real path name:"+name);
        }
    }
}
