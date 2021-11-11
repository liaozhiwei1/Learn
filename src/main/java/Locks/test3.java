package Locks;

import org.openjdk.jol.info.ClassLayout;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: test3
 * @package: Locks
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/11/11 10:51 上午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class test3 {


    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
                lock.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        }, "Thread1").start();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
}
