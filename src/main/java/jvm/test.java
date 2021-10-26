package jvm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: test
 * @package: jvm
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/7/21 10:48 上午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class test {
    private Lock lock =new ReentrantLock();
    private Lock lock1 = new ReentrantLock();
    public static  void  main(String[] args) throws InterruptedException {
//        List list = new LinkedList();
//        for (; ; ) {
//            byte[] bytes = new byte[1024];
//            Thread.sleep(200);
//            list.add(bytes);
//        }
//        test test = new test();
//        new Thread(()->{
//            try {
//                test.t();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(()->{
//            try {
//                test.t1();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            byte[] objects = new  byte[1024];
            list.add(objects);
        }
    }

/*    public void t() throws InterruptedException {
        lock.lock();
        while (true){

        }
    }

    public void t1() throws InterruptedException {
        lock.lock();
    }*/
}
