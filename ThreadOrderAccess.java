package Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @create 2020-03-28 20:27
 * 多线程之间按顺序调用实现A->B->C->A
 */

class ShareSource{
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    public void printA(){ //A线程
        lock.lock();
        try{
            //判断
        while(flag!=1){
                c1.await();
        }
            //干活
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flag=2;
            c2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }
    public void printB(){ //B线程
        lock.lock();
        try{
            //判断
            while(flag!=2){
                c2.await();
            }
            //干活
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flag=3;
            c3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }
    public void printC(){ //C线程
        lock.lock();
        try{
            //判断
            while(flag!=3){
                c3.await();
            }
            //干活
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flag=1;
            c1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }
}

public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareSource shareSource = new ShareSource();
        new Thread(() -> {
            for (int i = 0; i <=3; i++) {
                shareSource.printA();
            }
      },"A").start();
        new Thread(() -> {
            for (int i = 0; i <=3; i++) {
                shareSource.printB();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i <=3; i++) {
                shareSource.printC();
            }
        },"C").start();
    }
}
