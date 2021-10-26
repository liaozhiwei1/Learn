package Locks;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: MyAqs
 * @package: Locks
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/10/15 3:17 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
class MyAqs extends AbstractQueuedSynchronizer {

    public void lock() {
        this.lock(1);
    }

    public void lock(int arg) {
        super.acquire(arg);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        int state = super.getState();
        if (state == 0) {
            if (!super.hasQueuedPredecessors() && compareAndSetState(0, arg)) {
                return true;
            }
        }
        return false;
    }

    public void unLock(int arg) {
        super.release(1);
    }

    @Override
    protected boolean tryRelease(int arg) {
        boolean free = false;
        int s = super.getState() - arg;
        if (Thread.currentThread() != super.getExclusiveOwnerThread()) {
            throw new RuntimeException("不是该线程持有的锁");
        }
        if (s == 0) {
            free = true;
            super.setExclusiveOwnerThread(null);
        }
        super.setState(s);
        return free;
    }

    public static void main(String[] args) {
        MyAqs myAqs = new MyAqs();
        int x = 0;
        Test1 test = new Test1(x);
        for (int i = 0; i < 100; i++) {
            test.start();
        }
    }
}

class Test1 extends Thread{
    int i;

    public Test1(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        i++;
        System.out.println(i);
    }
}