package Locks;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version: 1.0
 * @description: 公平锁与非公平锁synchronized和ReentrantLock
 * 公平锁:先来先执行，排队执行
 * 非公平锁:先尝试直接获取锁，不成功在排队(效率高，但是会出现饥饿现象)
 *
 *
 * Synchronized：
 *      锁是对象不是代码
 *      锁对象  class也属于对象
 *      锁升级
 *          偏向锁 -> 自旋锁（消耗cpu） -> 重量级锁(向操作系统枷锁  wait()等方法进入等待队列)
 * @author: zhiwei.liao
 * @create: 2020-11-05 15:16:07
 **/
public class Locks1 {

    int a = 0;
    Lock reentrantLock = new ReentrantLock(); //默认创建非公平锁
    Lock reentrantLock1 = new ReentrantLock(true); //创建公平锁
    public static void main(String[] args) {
        Locks1 locks1 = new Locks1();
        locks1.test1();
        LongAdder longAdder = new LongAdder();
    }

    public void test1() {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    this.reentrantLock1.lock();
                    for (int j = 0; j < 1000; j++) {
                        this.a++;
                    }
                } finally {
                    this.reentrantLock1.unlock();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {

        }
        System.out.println(this.a);
    }

    public void test2(){
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (this) {
                    for (int j = 0; j < 1000; j++) {
                        this.a++;
                    }
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {

        }
        System.out.println(this.a);
    }
}
