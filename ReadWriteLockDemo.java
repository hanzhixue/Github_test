import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @author Administrator
 * @create 2020-03-28 19:45
 */
class MyCache{
    volatile Map<String,String> map = new HashMap<>();
   // Lock lock = new ReentrantLock();
   ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void write(String key ,String value){
        try{
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"\t准备写入");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t结束写入:"+key);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            readWriteLock.writeLock().unlock();
        }


    }

    public void read(String key){
        try{
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"\t准备读取");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t结束读取:"+result);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            readWriteLock.readLock().unlock();
        }

    }

}



public class ReadWriteLockDemo {
    public static void main(String[] args) {
       MyCache myCache =new MyCache();
        for (int i = 1; i <=10 ; i++) {
            final int k = i;
            new Thread(() -> {
                   myCache.write(k+"",k+"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <=10 ; i++) {
            final int k = i;
            new Thread(() -> {
                myCache.read(k+"");
            },String.valueOf(i)).start();
        }
    }
}
