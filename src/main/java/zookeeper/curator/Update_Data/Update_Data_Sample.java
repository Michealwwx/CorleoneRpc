package zookeeper.curator.Update_Data;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @ClassName Update_Data_Sample
 * @Description 更新数据；
 * @Author wuwenxiang
 * @Date 2019-04-17 20:57
 * @Version 1.0
 **/
public class Update_Data_Sample {
    static String path = "/getData";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();

    /**
     * 注意：withVersion是用来实现CAS的。
     * @throws Exception
     */
    @Test
    public void test_update_data() throws Exception {
        client.start();
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(path);
        //第一次采用最新的stat变量进行更新操作，更新成功；
        //client.setData().withVersion(stat.getVersion()).forPath(path).getVersion();
        //第二次采用过期的stat变量进行更新，抛出异常；KeeperErrorCode = BadVersion
        try{
            client.setData().withVersion(stat.getVersion()).forPath(path,"newData".getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
