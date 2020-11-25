package Thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-11-17 10:00:56
 **/
//1
class Thread1 implements Runnable{

    @Override
    public void run() {
        Collections.synchronizedList(new ArrayList<>());
        AtomicReference atomicReference = new AtomicReference(new ArrayList<>());
        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();
    }
}
//2
class  Thread2 implements Callable{

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println("正在计算");
            java.lang.Thread.sleep(1000);
        }
        return 1024+"";
    }
}
//3 class Thread3 extends Thread

//4 线程池


public class Thread{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread2 thread2 = new Thread2();
        FutureTask<String> futureTask = new FutureTask(thread2);
        new java.lang.Thread(futureTask,"aaa").start();
        for (int i=0;i<10;i++) {
            java.lang.Thread.sleep(500);
            System.out.println("1111");
        }
        System.out.println(futureTask.get());
    }
}