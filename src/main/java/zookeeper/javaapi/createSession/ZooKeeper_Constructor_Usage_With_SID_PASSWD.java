package zookeeper.javaapi.createSession;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: ZooKeeper_Constructor_Usage_With_SID_PASSWD
 * @Description: 创建一个复用sessionId 和 session
 * @Author: wuwx
 * @Date: 2019-04-15 15:39
 **/
public class ZooKeeper_Constructor_Usage_With_SID_PASSWD implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new ZooKeeper_Constructor_Usage_With_SID_PASSWD());
        countDownLatch.await();
        long sessionId = zooKeeper.getSessionId();
        byte[] passWd = zooKeeper.getSessionPasswd();
        //使用错误的sessionId和密码
        zooKeeper = new ZooKeeper("127.0.0.1:2181",
                5000, new ZooKeeper_Constructor_Usage_With_SID_PASSWD(),
                1l, "test".getBytes());

        //使用正确的sessionId和密码
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000,
                new ZooKeeper_Constructor_Usage_With_SID_PASSWD(), sessionId, passWd);
        Thread.sleep(Integer.MAX_VALUE);


    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("收到监听事件：" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}
