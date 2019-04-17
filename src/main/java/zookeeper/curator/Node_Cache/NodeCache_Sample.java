package zookeeper.curator.Node_Cache;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName NodeCache_Sample
 * @Description 事件监听；Cache是curator对事件监听的包装，其对事件的监听其实可以近似看做
 *      是一个本地缓存视图和远程zk视图的对比过程；同时curator能够自动为开发人员处理反复注册监听；
 *      Cache分类： 节点监听，子节点监听；
 * @Author wuwenxiang
 * @Date 2019-04-17 21:45
 * @Version 1.0
 **/
public class NodeCache_Sample {
    static String path = "/nodeCache";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .sessionTimeoutMs(5000)
            .build();

    /**
     * 处理事件监听；节点监听  监听数据节点的内容变更，也能监听指定节点是否存在，如果节点被删除则无法触发监听；
     */
    @Test
    public void testNodeCache() throws Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(path,"data_cacheNode".getBytes());

        /**
         * 两种构造：
         * 1.NodeCache(CuratorFrameWork client,String path,boolean t)  //该boolean值表示是否进行数据压缩；
         * 2.NodeCache(CuratorFrameWork client,String path)
         */
        final NodeCache nodeCache = new NodeCache(client,path,false);
        //该boolean值表示buildInitial，默认false，如果改为true表示NodeCache在第一次启动的
        // 时候就立刻从Zk上读取对应节点的数据内容，并保存在cache中；
        nodeCache.start(true);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("节点数据更新了，新数据："+new String(nodeCache.getCurrentData().getData()));
            }
        });

        client.setData().forPath(path,"更新了".getBytes());
        TimeUnit.SECONDS.sleep(1);
        //如果数据被删除，curator就无法触发NodeCacheLister
        client.delete().deletingChildrenIfNeeded().forPath(path);
        TimeUnit.MINUTES.sleep(2);
    }

    /**
     * 子节点事件监听；主要用来监听子节点的新增，数据变更，删除等三类事件类型
     */
    @Test
    public void testChildNodeCache() throws Exception {
        client.start();
        final PathChildrenCache childrenCache = new PathChildrenCache(client,path,true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                switch (pathChildrenCacheEvent.getType()){
                    case CHILD_ADDED:
                        System.out.println("事件：Child_Added:"+pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("事件：Child_Removed:"+pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("事件：Child_Updated:"+pathChildrenCacheEvent.getData().getPath());
                        break;
                }
            }
        });
        client.create().withMode(CreateMode.PERSISTENT)
                .forPath(path);
        TimeUnit.SECONDS.sleep(1);
        //创建子节点事件；
        client.create().withMode(CreateMode.PERSISTENT)
                .forPath(path+"/child","myChild".getBytes());
        TimeUnit.SECONDS.sleep(1);
        //更新子节点内容事件；
        client.setData().forPath(path+"/child","laowang".getBytes());
        TimeUnit.SECONDS.sleep(1);
        //删除子节点事件；
        client.delete().forPath(path+"/child");
        TimeUnit.SECONDS.sleep(1);

        TimeUnit.MINUTES.sleep(10);
    }
}
