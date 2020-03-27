package Test;

/**
 * @author hanzhixue
 * @create 2020-03-27 8:57
 */
class Tickets implements Runnable {
    int ticket = 50;

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    private synchronized void show() {

            synchronized (this) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "正在出售第" + ticket-- + "张票");
            }
    }
}
public class Stamp {
    public static void main(String[] args) {
        Runnable r = new Tickets();
        Thread thread = new Thread(r);
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread.start();
        thread1.start();
        thread2.start();
    }
}
