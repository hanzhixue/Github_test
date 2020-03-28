package Test;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Administrator
 * @create 2020-03-28 19:16
 */
public class NotSafeDemo {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i <25 ; i++) {
            new Thread(() -> {
                        list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
                    },String.valueOf(i)).start();
        }
    }
}
