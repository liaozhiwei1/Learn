package LeetCode;

import java.util.*;

/**
 * @version: 1.0
 * @description: 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: zhiwei.liao
 * @create: 2020-11-30 10:46:26
 **/
public class Solution {
    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的        数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int i1 = target - nums[i];
            Integer integer = map.get(i1);
            if (integer != null && !integer.equals(i)) {
                res[0] = i;
                res[1] = integer;
                return res;
            }
        }
        return null;
    }

    /**
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return num;
            } else {
                map.put(num, 0);
            }
        }
        return 0;
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请       完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > target) {
                    break;
                }
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        return s.replace("s", "%20");
    }

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] result = new int[list.size()];
        int j = 0;
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = list.get(j);
            j++;
        }
        return result;
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     */
    int[] preorder;
    HashMap<Integer, Integer> dic = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++)
            dic.put(inorder[i], i);
        return recur(0, 0, inorder.length - 1);
    }

    TreeNode recur(int root, int left, int right) {
        if (left > right) return null;                          // 递归终止
        TreeNode node = new TreeNode(preorder[root]);          // 建立根节点
        int i = dic.get(preorder[root]);                       // 划分根节点、左子树、右子树
        node.left = recur(root + 1, left, i - 1);              // 开启左子树递归
        node.right = recur(root + i - left + 1, i + 1, right); // 开启右子树递归
        return node;                                           // 回溯返回根节点
    }

    /**
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。
     *
     * @param n
     * @return
     */

    public int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int i = fib(n - 1) + fib(n - 2);
            if (i > 1000000007) {
                return 1;
            }
            return i;
        }
    }


    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            min = (min > numbers[i]) ? numbers[i] : min;
        }
        return min;
    }

    /**
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
     * <p>
     * [["a","b","c","e"],
     * ["s","f","c","s"],
     * ["a","d","e","e"]]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param board
     * @param word
     * @return
     */
    char[][] boa;
    char[] wo;

    public boolean exist(char[][] board, String word) {
        this.boa = board;
        this.wo = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (this.doExist(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean doExist(int x, int y, int k) {
        if (k > wo.length - 1) {
            return true;
        }
        if (x < 0 || y < 0 || x > boa.length - 1 || y > boa[0].length - 1 || boa[x][y] == ' ') {
            return false;
        }
        if (boa[x][y] == wo[k]) {
            char c = boa[x][y];
            boa[x][y] = ' ';
            if (doExist(x - 1, y, k + 1) || doExist(x + 1, y, k + 1) || doExist(x, y - 1, k + 1) || doExist(x, y + 1, k + 1)) {
                return true;
            } else {
                boa[x][y] = c;
            }
        }
        return false;
    }

    /**
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    int[][] checkered;
    int count;

    public int movingCount(int m, int n, int k) {
        checkered = new int[m][n];
        doMovingCount(0, 0, k);
        return count;
    }

    public void doMovingCount(int m, int n, int k) {
        int x = k;
        if (n < 0 || m < 0 || m > checkered.length - 1 || n > checkered[m].length - 1) {
            return;
        }
        if (checkered[m][n] == 1) {
            return;
        }
        int m1 = m;
        int n1 = n;
        while (m1 != 0 || n1 != 0) {
            k = k - (m1 % 10 + n1 % 10);
            m1 = m1 / 10;
            n1 = n1 / 10;
        }
        if (k >= 0) {
            if (checkered[m][n] != 1) {
                count++;
            }
            checkered[m][n] = 1;
            doMovingCount(m - 1, n, x);
            doMovingCount(m, n - 1, x);
            doMovingCount(m + 1, n, x);
            doMovingCount(m, n + 1, x);
        }
    }


    /**
     * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
     * <p>
     *  
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int i = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                i++;
            }
            n = n >>> 1;
        }
        return i;
    }

    /**
     * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        double result = 1.0;
        for (int i = n; i != 0; i /= 2, x *= x) {
            if (i % 2 != 0) {
                result *= x;
            }
        }
        return n < 0 ? 1.0 / result : result;
    }

    /**
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     *
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        int j = (int) Math.pow(10, n);
        int[] result = new int[j];
        for (int i = 0; i <= j; i++) {
            result[i] = i + 1;
        }
        return result;
    }


    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * <p>
     * 返回删除后的链表的头节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode node1 = null;
        ListNode temp = head;
        while (head != null) {
            if (head.val == val) {
                if (node1 == null) {
                    return head.next;
                } else {
                    node1.next = head.next;
                    break;
                }
            } else {
                node1 = head;
                head = head.next;
            }
        }
        return temp;
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @return
     */
    public int[] exchange(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 0) {
                for (int j = i + 1; j < nums.length; j++) {
                    if ((nums[j] & 1) == 1) {
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        break;
                    }
                }
            }
        }
        return nums;
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        for (int i = 0; i < k - 1; i++) {
            stack.pop();
        }
        return stack.pop();
    }

    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode listNode = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = listNode;
            listNode = head;
            head = next;
        }
        return listNode;
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 思路，将piont 看成需要填数据的位置，依次往后填新数据
     * @param nums
     * @return
     */

    public int removeDuplicates(int[] nums) {
        int point = 0;
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0){
                point ++;
                continue;
            }
            if (nums[i-1] == nums[i]){
                count++;
                if (count>2){
                    continue;
                }else {
                    nums[point] = nums[i];
                    point++;
                }
            }else {
                count = 1;
                nums[point] = nums[i];
                point++;
            }
        }
        return point;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,1,1,2,2,3,3,3};
        System.out.println(solution.removeDuplicates(nums));
    }


}
