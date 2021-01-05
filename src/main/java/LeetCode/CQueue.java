package LeetCode;

import java.util.Stack;

/**
 * @version: 1.0
 * @description:用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: zhiwei.liao
 * @create: 2021-01-04 15:51:41
 **/
public class CQueue {

    Stack<Integer> stack1 = new Stack();

    Stack<Integer> stack2 = new Stack();

    public CQueue() {

    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack1.empty()){
            return -1;
        }
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
        int result = stack2.pop();
        while (!stack2.empty()){
            stack1.push(stack2.pop());
        }
        return result;
    }
}
