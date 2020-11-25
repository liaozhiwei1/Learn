package Queue;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version: 1.0
 * @description: 基于阻塞队列     生产消费者模式，消息组件的基本原理，线程池
 *               线程   操作   资源类
 *               判断   干活   通知
 *               防止虚假唤醒机制
 *               1.0: synchronized -> wait -> notify
 *               2.0: lock -> await -> sinqal
 *               3.0: BlockingQueue（自动睡眠和唤起线程）
 *
 *               synchronized 锁升级  无锁-》偏向锁-》轻量级锁(不动用cpu进行分配的锁，如自旋锁) -》 重量级锁
 *
 *               synchronized和lock的区别
 *               1.synchronized是关键字属于jvm层面   lock是具体类(java.util.concurrent.locks) 属于api层面
 *               2.synchronized不需要用户手动解锁代码执行完成之后会自动解锁 上锁(monitorenter) 解锁(monitorexit)  locke需要用户手动解锁否则会死锁
 *               3.synchronized不可中断 lock可以中断，在lockInterruptibly()调用interrupt()方法可中断
 *               4.synchronized非公平锁  lock可以是非公平锁也可以是公平锁
 *               5.synchronized不能指定唤醒的线程  lock可以通过condition精确唤醒指定线程
 * @author: zhiwei.liao
 * @create: 2020-11-16 13:36:48
 **/
class Resource {
    public BlockingQueue<String> queue;

    public Resource(BlockingQueue<String> queue) {
        this.queue = queue;
    }
    public void produce(String message) {
        for (int a = 0; a < 5; a++) {
            try {
                System.out.println("放入队列成功！");
                queue.offer(message, 1, TimeUnit.HOURS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void customer() throws InterruptedException {
        for (int a = 0; a < 5; a++) {
            Thread.sleep(1000);
            String poll = queue.take();
            System.out.println(poll);
        }
    }
}

class Resource1{
    private volatile int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        try {
            lock.lock();
            //判断
            while (number != 0){
                condition.await();
            }
            //干活
            number++;
            System.out.println(number);

            //通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void customer() throws InterruptedException {

        try {
            lock.lock();
            //判断
            while (number == 0){
                condition.await();
            }
            //干活
            number--;
            System.out.println(number);
            //通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class queue1 {
    public static void main(String[] args) {
        //3.0
        Resource resource = new Resource(new SynchronousQueue<>());
        new Thread(() -> {
            try {
                resource.customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> resource.produce("11111")).start();

        //2.0
        Resource1 resource1 = new Resource1();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    resource1.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    resource1.customer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //1.0  synchronized wait() notifyAll()
    }
}
