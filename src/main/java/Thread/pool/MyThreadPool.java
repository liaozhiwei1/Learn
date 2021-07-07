package Thread.pool;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: MyThreadPool
 * @package: Thread.pool
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/7/7 11:58 上午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class MyThreadPool {
    private LinkedList<Game> pool = new LinkedList<>();
    public AtomicInteger integer = new AtomicInteger(0);

    public MyThreadPool(int size) {
        for (int i = 0; i < size; i++) {
            pool.addLast(new Game());
        }
    }


    public Game getThread(long mi) throws InterruptedException {
        synchronized (pool) {
            if (mi < 0) {
                while (pool.size() == 0) {
                    integer.addAndGet(1);
                    pool.wait();
                }
                return pool.remove();
            } else {
                long s = System.currentTimeMillis() + mi;
                long m = mi;

                while (pool.size() == 0 && m > 0) {
                    integer.addAndGet(1);
                    pool.wait(m);
                    m = s - System.currentTimeMillis();
                }
                if (m<0){
                    System.out.println(Thread.currentThread()+"等待超时了!");
                }
                return pool.size() == 0 ? null : pool.remove();
            }
        }
    }

    public boolean pushThread(Game thread) {
        synchronized (pool) {
            if (pool != null) {
                System.out.println(this.integer.get());
                pool.addLast(thread);
                pool.notifyAll();
                return true;
            }
        }
        return false;
    }
}
