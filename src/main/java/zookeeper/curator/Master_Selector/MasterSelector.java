package zookeeper.curator.Master_Selector;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

/**
 * @ClassName MasterSelector
 * @Description Master选举
 * 思路：选择一个根结点，例如/master_select,多台机器同时向该节点创建一个子节点/master_select/lock
 * 利用zk的特性，最终只有一台机器能够创建成功，成功的那台机器就是master；
 *
 * @Author wuwenxiang
 * @Date 2019-04-17 22:30
 * @Version 1.0
 **/
public class MasterSelector {
    static String path = "/master_select";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .sessionTimeoutMs(5000)
            .build();

    /**
     * 测试选举master；
     */
    @Test
    public void testMasterSelect() throws InterruptedException {
        client.start();
        LeaderSelector selector = new LeaderSelector(client, path, new LeaderSelectorListener() {
            /**
             * curator会在竞争到master后自动调用该方法，一旦执行完该方法后，
             * curator会理解释放master权利，然后重新开始新一轮的选举；
             * @param curatorFramework
             * @throws Exception
             */
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("成为Master角色");
                Thread.sleep(1);
                System.out.println("完成master操作，释放master权利");
            }
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                System.out.println("连接状态变更"+newState.name());
            }
        });
        selector.autoRequeue();
        selector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }



}
