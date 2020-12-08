package Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version: 1.0
 * @description: 可重入锁(递归锁):获取一次锁在锁范围内的代码段里面可重复获取同一把锁;可以预防死锁问题; ReentrantLock，synchronized
 *               不可重入锁:CAS
 * @author: zhiwei.liao
 * @create: 2020-11-06 15:58:15
 **/
public class Locks2 {

    int a = 0;
    Lock lock1 = new ReentrantLock();
    public static void main(String[] args) {
        Locks2 locks = new Locks2();
        new Thread(()->{
            try {
                locks.del();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                locks.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        locks.syncAdd();
    }

    public void add() throws InterruptedException {
        try {
            this.lock1.lock();
            System.out.println("获取lock1成功");
            this.a++;
            del();
        } finally {
            this.lock1.unlock();
            System.out.println("释放lock1成功");
        }
    }

    public void del() throws InterruptedException {
        try {
            this.lock1.lock();
            System.out.println("del获取lock1成功");
            this.a--;
            Thread.sleep(1000);
        } finally {
            this.lock1.unlock();
            System.out.println("del释放lock1成功");
        }
    }

    public synchronized void syncAdd(){
        System.out.println("sync获取锁");
        syncDel();
    }

    public synchronized void syncDel(){
        System.out.println("再次获取sync锁");
    }
}
