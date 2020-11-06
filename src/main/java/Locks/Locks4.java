package Locks;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @version: 1.0
 * @description: 自旋锁:旋转等待获取锁。
 *                      优点:没有线程的切换,速度快;
 *                      缺点:旋转等待的过程消耗cpu资源,当等待线程过多会过度消耗CPU资源;
 * @author: zhiwei.liao
 * @create: 2020-11-06 17:43:51
 **/
public class Locks4 {
    volatile int a = 0;
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        Locks4 locks4 = new Locks4();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    locks4.add();
                }
            }, "test" + i).start();
        }
    }


    public void add() {
        try {
            while (!atomicReference.compareAndSet(null, Thread.currentThread())) {
            }
            this.a++;
            System.out.println(Thread.currentThread().getName() + "：" + this.a);
        } finally {
            atomicReference.compareAndSet(Thread.currentThread(), null);
        }
    }
}
