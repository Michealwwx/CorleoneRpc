package zookeeper.javaapi.createSession;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: Zookeeper_Constructor_Usage_Simple
 * @Description: 创建一个最基本的zookeeper会话实例；
 * @Author: wuwx
 * @Date: 2019-04-15 15:09
 **/
public class Zookeeper_Constructor_Usage_Simple implements Watcher {

    public static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent event) {
        System.out.println("收到监听事件："+event);
        if(Event.KeeperState.SyncConnected == event.getState()){
            connectedSemaphore.countDown();
        }
    }

    public static void main(String[] args) {
        try {
            //注意：Zookeeper客户端和服务端会话的建立是一个异步的过程，也就是说在程序中，构造方
            //会在处理完客户端初始化工作后立即返回，大多数情况下，此时并没有真正建立好一个可用的会话，在会话的生命周期中处于connection状态。
            ZooKeeper zookeeper = new ZooKeeper("127.0.0.1:2181",5000,new Zookeeper_Constructor_Usage_Simple());
            System.out.println("客户端状态："+zookeeper.getState());
            connectedSemaphore.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper session 已经建立");
    }
}
