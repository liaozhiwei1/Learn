package Locks;

import sun.misc.Unsafe;

import java.util.Random;
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
    Lock reentrantLock = new ReentrantLock(); //默认创建非公平锁
    Lock reentrantLock1 = new ReentrantLock(true); //创建公平锁
    private volatile static int i = 0;

    public static void main(String[] args) {
  /*      Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                while (i < 100) {
                    i++;
                    System.out.println(i + Thread.currentThread().getName());
                    o.notifyAll();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (o) {
                while (i < 100) {
                    i++;
                    System.out.println(i+ Thread.currentThread().getName());
                    o.notifyAll();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/

        double max = 100.00;
        for (int j = 0; j < 2; j++) {
            double re = a(0,max);
            System.out.println(re);
            max = max -re;
        }
        System.out.println((double) Math.round(max*100)/100);
    }

    public static double a(double min,double max){
        Random random = new Random();
        double re = min+(random.nextDouble())*(max-min);
        return (double) Math.round(re*100)/100;
    }



/*

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
*/
}
