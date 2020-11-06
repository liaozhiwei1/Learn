package Locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @version: 1.0
 * @description: 独占锁: 只能一个线程访问
 *               共享锁: 可以多个线程访问
 *               读写锁: 独占锁加共享锁(读写锁)；读写分离
 * @author: zhiwei.liao
 * @create: 2020-11-06 17:10:13
 **/
public class Locks3 {

    int a = 0;
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        Locks3 locks1 = new Locks3();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {locks1.add();},i+"").start();
        }
        for (int i = 10; i < 20; i++) {
            new Thread(() -> {locks1.get();},i+"").start();
        }
    }


    public void add() {

        try {
            this.readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "获取写锁");
            this.a++;
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            this.readWriteLock.writeLock().unlock();
        }
    }

    public void get() {

        try {
            this.readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "获取读锁");
            System.out.println(a);
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            this.readWriteLock.readLock().unlock();
        }
    }
}
