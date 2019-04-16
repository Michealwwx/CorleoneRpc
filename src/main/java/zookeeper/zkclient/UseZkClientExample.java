package zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * @ClassName: UseZkClientExample
 * @Description: 使用zk client操作zk
 * @Author: wuwx
 * @Date: 2019-04-15 14:43
 **/
public class UseZkClientExample {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181",5000);
        String path = "/zk-book/c1";
        //true 表示需要递归创建父节点；
        zkClient.createPersistent(path,true);
    }

}
