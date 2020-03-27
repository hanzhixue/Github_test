package Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @create 2020-03-27 19:53
 */
class Air{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increament()throws Exception {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
        public void decreament()throws Exception{
            lock.lock();
            try{
                while(number==0){
                    condition.await();
                }
                number--;
                System.out.println(Thread.currentThread().getName()+"\t"+number);
                condition.signalAll();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
  /*  public synchronized  void increament()throws Exception{
        while(number!=0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }
    public synchronized  void decreament()throws Exception{
        while (number == 0) {
              this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }*/
}

public class temp {
    public static void main(String[] args) {
        Air air = new Air();
        new Thread(()->{
            for (int i = 0; i <=10; i++) {
                try {
                    air.increament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
         },"A").start();
        new Thread(()->{
            for (int i = 0; i <=10; i++) {
                try {
                    air.decreament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i <=10; i++) {
                try {
                    air.decreament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i <=10; i++) {
                try {
                    air.decreament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
