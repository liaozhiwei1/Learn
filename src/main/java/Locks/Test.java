package Locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: Test
 * @package: Locks
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/7/8 4:07 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Test {

    public static void main(String[] args) {
//        Demo demo = new Demo();
//        Thread thread1 = new Thread(() -> {
//            while (demo.size() != 5){
//                LockSupport.park();
//            }
//            System.out.println(Thread.currentThread().getName()+"结束");
//        },"Thread2");
//        thread1.start();
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                demo.add();
//                if (demo.size() == 5) {
//                    LockSupport.unpark(thread1);
////                    try {
////                        Thread.sleep(1000);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
//                }
//            }
//        }).start();

        Demo demo = new Demo();
        Object lock = new Object();
        new Thread(() -> {
            System.out.println("t2 开始");
            synchronized (lock) {
                while (demo.size() != 5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notifyAll();
            }
            System.out.println("t2 结束");
        }).start();
        new Thread(() -> {
            System.out.println("t1 开始");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    demo.add();
                    if (demo.size() == 5) {
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("t1 结束");
        }).start();
    }
}

class Demo {
    public List<Object> size = new ArrayList<>();

    public void add() {
        System.out.println(size.size());
        size.add(new Object());
    }

    public int size() {
        return size.size();
    }
}
