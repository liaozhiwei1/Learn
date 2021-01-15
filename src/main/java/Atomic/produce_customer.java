package Atomic;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2021-01-14 16:33:38
 **/
public class produce_customer {
    //第一种采用阻塞队列形成消费者生产者模式
    class Resource {
        public BlockingQueue<String> queue;

        public Resource(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        public void produce(String message) throws InterruptedException {
            //生产消息并放入队列
            queue.offer(message, 1, TimeUnit.HOURS);
        }


        public void customer() throws InterruptedException {
            while (true) {
                Thread.sleep(1000);
                String poll = queue.take();
                //处理逻辑
                System.out.println(poll);
            }
        }
    }

    //第二种方式才用锁的方式相互通知干活
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
                    LockSupport.park();
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
}
