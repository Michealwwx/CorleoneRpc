import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MapInvoker
 * @Description: TODO
 * @Author: wuwx
 * @Date: 2019-04-17 15:14
 **/
public class MapInvoker {
        public static void main(String[] args) throws Exception {
            Map map = new HashMap();
            map = MapProxy.newProxy(map);
            map.put("a", "b");
            map.put("a", "c");

        }
    }

    class MapProxy implements InvocationHandler {
        private Map map;

        public static Map newProxy(Map map) {
            return new MapProxy(map).getProxy();
        }

        private MapProxy(Map map) {
            this.map = map;
        }

        private Map getProxy() {
            return (Map) Proxy.newProxyInstance(Map.class.getClassLoader(), new Class[] { Map.class }, this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = null;

            String methodName = method.getName();
            if (methodName.equalsIgnoreCase("put")) {

                this.beforePut(args);

                result = method.invoke(map, args);

                this.afterPut(result);
            }

            return result;
        }

        protected void beforePut(Object[] args) {
            System.out.println("put: " + Arrays.toString(args));
        }

        protected void afterPut(Object result) {
            System.out.println("return: " + result);
        }
    }



