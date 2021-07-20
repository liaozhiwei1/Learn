package Locks;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: Test2
 * @package: Locks
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/7/9 10:56 上午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Test2 {

    public static void main(String[] arg){
        MySyncContainer<Object> mySyncContainer = new MySyncContainer<>(20);
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                while (true){
                    try {
                        mySyncContainer.set(new Object());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true){
                    try {
                        mySyncContainer.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

class MySyncContainer<T>{
    private List<T> list = new LinkedList<>();
    int count;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public MySyncContainer(int count){
        this.count = count;
    }

    public /*synchronized*/ void set(T t) throws InterruptedException {

        try {
            lock.lock();
            while (list.size() >= count) {
                System.out.println("当前容量" + list.size() + "容量已满!" + Thread.currentThread().getName() + "进入等待");
//            wait();
                producer.await();
            }
            System.out.println("当前容量" + list.size() + Thread.currentThread().getName() + "添加成功");
            list.add(t);
            consumer.signalAll();
//        notifyAll();
        }finally {
            lock.unlock();
        }
    }

    public /*synchronized*/ T get() throws InterruptedException {
        T remove = null;
        try {
            lock.lock();
            while (list.size() <= 0) {
                System.out.println("容量为空!" + Thread.currentThread().getName() + "进入等待");
//            wait();
                consumer.await();
            }
            remove = list.remove(0);
            System.out.println("当前容量" + list.size() + Thread.currentThread().getName() + "获取成功");
//        notifyAll();
            producer.signalAll();
        }finally {
            lock.unlock();
        }
        return remove;
    }
}

