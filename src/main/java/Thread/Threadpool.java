package Thread;

import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;

import java.util.concurrent.*;

/**
 * @version: 1.0
 * @description: 线程池优势：线程复用，控制最大并发数，线程管理。
 * ThreadPoolExecutor: int corePoolSize,   //核心线程数，即空闲时也会保留的线程数 常驻参数
 * int maximumPoolSize,  //最大线程数，即池中最大线程数
 * long keepAliveTime,  //多余的空闲线程存活时间
 * TimeUnit unit,
 * BlockingQueue<Runnable> workQueue,  //采用的阻塞队列实例，默认为LinkedBlockingQueue
 * ThreadFactory threadFactory,
 * RejectedExecutionHandler handler
 * <p>
 * 线程数配置: cpu密集型 : cpu核数+1
 * io密集型:1.cpu核数*2  2.cpu核数/(1-阻塞系数)  注:阻塞系数是0.8-0.9 一般取用0.9
 * @author: zhiwei.liao
 * @create: 2020-11-17 11:25:29
 **/
public class Threadpool {

    public static void main(String[] args) {
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(2, 3, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), new MyHeader());
        try {
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(() -> {
                    try {
                        System.out.println(java.lang.Thread.currentThread().getName() + "");
//                        java.lang.Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    public void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService = Executors.newSingleThreadExecutor();
        executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 100; i++) {
                executorService.execute(() -> {
                    System.out.println(java.lang.Thread.currentThread().getName() + "");
                });
            }
        } finally {
            executorService.shutdown();
        }
    }
}

class MyHeader implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("进入处理");
//        executor.execute(r);
    }
}
