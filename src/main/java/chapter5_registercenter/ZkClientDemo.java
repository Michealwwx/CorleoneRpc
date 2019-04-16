package chapter5_registercenter;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ZkClientDemo
 * @Description: TODO
 * @Author: wuwx
 * @Date: 2019-04-13 10:54
 **/
public class ZkClientDemo {
    public static void main(String[] args) throws InterruptedException {
        String zkServers = "127.0.0.1:2181";
        int connectionTimeout=3000;
        ZkClient zkClient = new ZkClient(zkServers,connectionTimeout);
        String path="/zk-data";
        //若节点已经存在，则删除；
        if(zkClient.exists(path)){
            zkClient.delete(path);
        }
        //创建持久节点；
        zkClient.createPersistent(path);
        //节点写入数据
        zkClient.writeData(path,"test_data_1");
        //节点读取数据；
        String data = zkClient.<String>readData(path,true);
        System.out.println("节点读取数据："+data);

        //注册监听器，监听数据变化；
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object o) throws Exception {
                System.out.println("handlerDataChange,dataPath:"+dataPath+"data:"+data);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("handlerDataDeleted,dataPath:"+s);
            }
        });


        //修改数据;
        zkClient.writeData(path,"test_data_2");
        //删除节点：
        //zkClient.delete(path);
        //
        TimeUnit.SECONDS.sleep(200_000);

    }
}
