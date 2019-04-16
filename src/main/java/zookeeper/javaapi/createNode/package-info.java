package zookeeper.javaapi.createNode;
/**
 * 1.无论是同步还是异步接口，zookeeper都不支持递归创建，即无法在父节点不存在的情况下创建一个子节点；
 * 另外如果一个节点已经存在了，那么创建同名节点的时候，会抛出NodeExistException；
 *
 * 2.Zookeeper的节点内容只支持字节数组类型，即zookeeper不负责为节点内容进行序列化，
 *
 * 3.权限控制；
 *
 */