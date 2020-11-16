package Atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @version: 1.0
 * @description: Atomic原子类底层实现采用cas(自旋锁);抛出ABA问题  A->B->A compareAndSet返回为ture但是值已经改变  解决:加版本号
 * @author: zhiwei.liao
 * @create: 2020-11-06 17:42:51
 **/
public class AtomicInter {

    AtomicStampedReference<String > atomicStampedReference = new AtomicStampedReference<>("0", 0);
    AtomicReference<String > atomicReference = new AtomicReference<>("0");
    public static void main(String[] args) {

        AtomicInter atomicInter = new AtomicInter();
        atomicInter.noStampedReference();
        atomicInter.stampedReference();
    }


    public void  noStampedReference(){
        System.out.println(this.atomicReference.compareAndSet("0","2019"));
        System.out.println(this.atomicReference.compareAndSet("2019","0"));
        System.out.println(this.atomicReference.compareAndSet("0","2019"));
        System.out.println(this.atomicReference.get());
    }

    public void  stampedReference(){
        System.out.println(this.atomicStampedReference.compareAndSet("0","2019",0,1));
        System.out.println(this.atomicStampedReference.compareAndSet("2019","0",1,2));
        System.out.println(this.atomicStampedReference.compareAndSet("0","2019",0,1));
        System.out.println(this.atomicStampedReference.getReference());
    }
}
