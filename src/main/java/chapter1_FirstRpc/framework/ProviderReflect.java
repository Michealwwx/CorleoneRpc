package chapter1_FirstRpc.framework;

import org.apache.commons.beanutils.MethodUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: ProviderReflect
 * @Description: 服务的提供方，通过接收consumerProxy通过socket写入的参数，定位到具体的服务实现
 * 并通过java反射技术实现服务的调用，然后将结果写入socket，返回到consumerproxy
 * @Author: wuwx
 * @Date: 2019-04-11 13:25
 **/
public class ProviderReflect {
    private static final ExecutorService es = Executors.newCachedThreadPool();

    /**
     * 服务的发布
     *
     * @param service
     * @param port
     */
    public static void provider(final Object service, int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            es.execute(() -> {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    try {

                        //1.获取方法名
                        String methodName = inputStream.readUTF();
                        //2.获取发放参数
                        Object[] arguments = (Object[]) inputStream.readObject();
                        //输出对象流
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                        Object result = null;
                        try {
                            //调用方法；
                            result = MethodUtils.invokeExactMethod(service, methodName, arguments);
                            outputStream.writeObject(result);
                        } catch (Throwable e) {
                            outputStream.writeObject(result);
                            e.printStackTrace();
                        } finally {
                            outputStream.close();
                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        inputStream.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });

        }
    }

}
