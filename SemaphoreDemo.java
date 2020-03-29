package Test;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @create 2020-03-29 11:43
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore  semaphore = new Semaphore(3);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                boolean flag = false;
                try {
                    semaphore.acquire();
                    flag = true;
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(4));
                    System.out.println(Thread.currentThread().getName()+"\t离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (flag ) {
                        semaphore.release();
                    }


                }
            },String.valueOf(i)).start();
        }
    }
}
