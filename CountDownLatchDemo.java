package Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author hanzhixue
 * @create 2020-03-28 20:48
 *7个同学在教室上自习，要求班长锁门，必须要最后一个同学都离开教室了，班长才可以锁门
 */



public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(7);
        for (int i = 1; i <=7 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
                countDownLatch.countDown();
                    },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t班长离开教室");
    }
}
