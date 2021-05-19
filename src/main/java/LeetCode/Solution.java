package LeetCode;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.BinaryOperator;

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
     * <p>
     * 思路，将piont 看成需要填数据的位置，依次往后填新数据
     *
     * @param nums
     * @return
     */

    public int removeDuplicates(int[] nums) {
        int point = 0;
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                point++;
                continue;
            }
            if (nums[i - 1] == nums[i]) {
                count++;
                if (count > 2) {
                    continue;
                } else {
                    nums[point] = nums[i];
                    point++;
                }
            } else {
                count = 1;
                nums[point] = nums[i];
                point++;
            }
        }
        return point;
    }


    /**
     * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
     * <p>
     * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。
     * <p>
     * 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
     * <p>
     * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
     * <p>
     * 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
     * <p>
     * 思路：使用栈的数据结构模拟操作数据栈进行操作,保证先乘除，再加减
     *
     * @param N
     * @return
     */

    public int clumsy(int N) {
        int result = 0;
        int x = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(N);
        N = N - 1;
        while (N > 0) {
            if (x % 4 == 0) {
                stack.push(stack.pop() * N);
            } else if (x % 4 == 1) {
                stack.push(stack.pop() / N);
            } else if (x % 4 == 2) {
                stack.push(N);
            } else if (x % 4 == 3) {
                stack.push(-N);
            }
            x++;
            N--;
        }
        while (true) {
            if (stack.empty()) {
                return result;
            }
            result += stack.pop();
        }
    }

    /**
     * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
     * <p>
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
     * <p>
     * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * <p>
     * 思路：先按照旋转规则，进行排序，在用二分法进行查找
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {

        if (nums.length == 1) {
            return nums[0] == target;
        }
        int tage = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                tage = i - 1;
                break;
            }
        }
        if (target < nums[0]) {
            return dichotomySort(nums, target, 0, tage);
        } else {
            return dichotomySort(nums, target, tage, nums.length - 1);
        }


    }

    //二分法
    private boolean dichotomySort(int nums[], int target, int header, int tail) {
        if (header <= tail) {
            int mid = header + (tail - header) / 2;
            if (target == nums[mid]) {
                return true;
            } else if (target > nums[mid]) {
                return dichotomySort(nums, target, mid + 1, tail);
            } else if (target < nums[mid]) {
                return dichotomySort(nums, target, header, mid - 1);
            }
        }
        return false;
    }

    /**
     * 给你两个整数，n 和 start 。
     * <p>
     * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
     * <p>
     * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
     *
     * @param n
     * @param start
     * @return
     */
    public int xorOperation(int n, int start) {
        if (n == 1) {
            return start;
        }
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = start + 2 * i;
        }

        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            temp = temp ^ nums[i];
        }
        return temp;
    }

    /**
     * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        boolean db[][] = new boolean[s.length() + 1][p.length() + 1];
        db[0][0] = true;
        //init
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') db[0][i] = db[0][i - 2];
        }

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                        db[i][j] = db[i][j - 2] || db[i - 1][j] || db[i - 1][j - 2];  //当遇到*号是，存在三种情况，*号之前的赐福出现0次，一次和多次
                    } else {
                        db[i][j] = db[i][j - 2];
                    }
                } else {
                    if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                        db[i][j] = db[i - 1][j - 1];
                    } else {
                        db[i][j] = false;
                    }
                }
            }
        }

        return db[s.length()][p.length()];
    }

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param height
     */
    public int maxArea(int[] height) {
        int max = 0;
        /*for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                max = Math.max(max,Math.min(height[i],height[j])*(j-i));
            }
        }
        return max;*/

        int x = 0;
        int y = height.length - 1;
        while (x < y) {
            if (height[x] >= height[y]) {
                max = Math.max(max, height[y] * (y - x));
                y--;
            } else {
                max = Math.max(max, height[x] * (y - x));
                x++;
            }
        }
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/integer-to-roman/
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(4);
        stack.push(5);
        stack.push(9);
        stack.push(10);
        stack.push(40);
        stack.push(50);
        stack.push(90);
        stack.push(100);
        stack.push(400);
        stack.push(500);
        stack.push(900);
        stack.push(1000);
        Map<Integer, String> map = new HashMap<>();
        map.put(1000, "M");
        map.put(500, "D");
        map.put(100, "C");
        map.put(50, "L");
        map.put(10, "X");
        map.put(5, "V");
        map.put(1, "I");
        map.put(4, "IV");
        map.put(9, "IX");
        map.put(40, "XL");
        map.put(90, "XC");
        map.put(400, "CD");
        map.put(900, "CM");

        if (num == 4) {
            return "IV";
        } else if (num == 9) {
            return "IX";
        } else if (num == 40) {
            return "XL";
        } else if (num == 90) {
            return "XC";
        } else if (num == 400) {
            return "CD";
        } else if (num == 900) {
            return "CM";
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            int x = stack.pop();
            while (num > 0) {
                num = num - x;
                if (num > 0) {
                    stringBuffer.append(map.get(x));
                } else if (num < 0 && !stack.empty()) {
                    num += x;
                    x = stack.pop();
                } else {
                    stringBuffer.append(map.get(x));
                    return stringBuffer.toString();
                }
            }
            return stringBuffer.toString();
        }
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        if (nums[0] > 0 || nums[nums.length - 1] < 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> temp = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x > 0) {
                return temp;
            }
            int L = i + 1;
            int R = nums.length - 1;

            while (L < R) {
                int num = x + nums[L] + nums[R];
                if (num == 0) {
                    temp.add(Arrays.asList(x, nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++;
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--;
                    }
                } else if (num > 0) {
                    R--;
                } else if (num < 0) {
                    L++;
                }
            }
        }
        return temp;
    }

    /**
     * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
     * <p>
     * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
     * <p>
     * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
     * https://leetcode-cn.com/problems/decode-xored-permutation/
     * 异或特性，我们反推第一个值，能知道perm的数据，不知道排列，所有可以知道perm所有值得异或结果，由此推出第一个值
     *
     * @param encoded
     * @return
     */
    public int[] decode(int[] encoded) {
        int x2 = 1;
        for (int i = 2; i <= encoded.length + 1; i++) {
            x2 = x2 ^ i;
        }
        int x1 = encoded[encoded.length - 1];
        for (int i = encoded.length - 3; i > 0; i = i - 2) {
            x1 = x1 ^ encoded[i];
        }
        int x = x1 ^ x2;
        int[] ints = new int[encoded.length + 1];
        ints[0] = x;
        int tmpe = x;
        for (int i = 0; i < encoded.length; i++) {
            int y = tmpe ^ encoded[i];
            ints[i + 1] = y;
            tmpe = y;
        }
        return ints;
    }


    /**
     * https://leetcode-cn.com/problems/decode-xored-array/
     *
     * @param encoded
     * @param first
     * @return
     */
    public int[] decode(int[] encoded, int first) {
        int x = first;
        int[] ints = new int[encoded.length + 1];
        ints[0] = x;
        int tmpe = x;
        for (int i = 0; i < encoded.length; i++) {
            int y = tmpe ^ encoded[i];
            ints[i + 1] = y;
            tmpe = y;
        }
        return ints;
    }


    /**
     * https://leetcode-cn.com/problems/leaf-similar-trees/
     * 深度优先搜索算法
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return false;

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        dfs(root1, l1);
        dfs(root2, l2);

        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i) != l2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void dfs(TreeNode root, List<Integer> l1) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            l1.add(root.val);
        }

        dfs(root.left, l1);
        dfs(root.right, l1);
    }

    /**
     * https://leetcode-cn.com/problems/length-of-longest-fibonacci-subsequence/
     * 双指针
     *
     * @param arr
     * @return
     */
    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        /*int[][] dp = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length-1; i++) {
            dp[i][i + 1] = 2;
        }*/

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr.length - i - 1) <= max) {
                return max;
            }
            for (int j = i + 1; j < arr.length; j++) {
                int x = 2;
                int i1 = i;
                int j1 = j;
                while (map.containsKey(arr[i1] + arr[j1])) {
                    int temp = map.get(arr[i1] + arr[j1]);
                    i1 = j1;
                    j1 = temp;
                    max = Math.max(max, ++x);
                }
            }
        }
        return max;
    }


    /**
     * https://leetcode-cn.com/problems/walking-robot-simulation/
     *
     * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        int[] dir_x = {0, 1, 0, -1};
        int[] dir_y = {1, 0, -1, 0};
        int x = 0;
        int y = 0;
        int status = 0; //0,1,2,3 分别表示北，东，南，西
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            strings.add(obstacles[i][0] + "" + obstacles[i][1]);
        }

        int max = 0;
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == -1) {
                status += 1;
                if (status > 3) {
                    status = 0;
                }
            }
            if (commands[i] == -2) {
                status -= 1;
                if (status < 0) {
                    status = 3;
                }
            }
            if (commands[i] > 0) {
                for (int j = 0; j < commands[i]; j++) {
                    x = x + dir_x[status];
                    y = y + dir_y[status];
                    if (strings.contains(x + "" + y)) {
                        x = x - dir_x[status];
                        y = y - dir_y[status];
                        break;
                    }
                }
                max = Math.max(max, x * x + y * y);
            }
        }
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/koko-eating-bananas/
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        int min = 0;
        while (min < max) {
            double y = 0;
            int mod = Math.floorDiv((max - min), 2) + min;
            for (int i = 0; i < piles.length; i++) {
                y += piles[i] % mod == 0 ? piles[i] / mod : piles[i] / mod + 1;
                if (y > h) {
                    break;
                }
            }
            if (y > h) {
                min = mod + 1;
            } else if (y <= h) {
                max = mod;
            }
        }
        return min;
    }

    /**
     * https://leetcode-cn.com/problems/middle-of-the-linked-list/
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode temp = head;
        int count = 0;
        while (temp.next != null) {
            count++;
            temp = temp.next;
        }
        int i = count / 2 == 0 ? count / 2 + 1 : count / 2;
        for (int j = 0; j < i; j++) {
            head = head.next;
        }
        return head;
    }

    /**
     * https://leetcode-cn.com/problems/stone-game/
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        //init
        dp[0][piles.length - 1] = 0;

        for (int i = 0; i < piles.length; i++) {
            for (int j = piles.length - 1; j > i; j--) {
                dp[i][j - 1] = piles[j] - dp[i][j];
            }
        }
        return false;
    }


    /**
     * https://leetcode-cn.com/problems/nth-magical-number/
     * n = X/a+X/b-最小公倍数的个数
     */
    public int nthMagicalNumber(int n, int a, int b) {
        //先找到最小公倍数
        int gdc = a * b / gdc(Math.min(a, b), Math.max(a, b));
        int left = 0;
        int right = Integer.MAX_VALUE;
        while (right > left) {
            int mid = (right - left) / 2 + left;
            int i = mid / a + mid / b - mid / gdc;
            if (i >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //最小公倍数
    public int gdc(int a, int b) {
        if (b == 0) return a;
        return gdc(b, a % b);
    }


    /**
     * https://leetcode-cn.com/problems/profitable-schemes/
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp1 = new int[profit.length][profit.length];
        for (int i = 0; i < profit.length; i++) {
            dp1[i][i] = profit[i];
        }
        for (int i = 0; i < profit.length; i++) {
            for (int j = i + 1; j < profit.length; j++) {
                dp1[i][j] = profit[j] + dp1[i][j - 1];
            }
        }

        int count = 0;
        int[][] dp2 = new int[group.length][group.length];
        for (int i = 0; i < group.length; i++) {
            dp2[i][i] = group[i];
            if (dp1[i][i] >= minProfit && dp2[i][i] < n) {
                count++;
            }
        }
        for (int i = 0; i < group.length; i++) {
            for (int j = i + 1; j < group.length; j++) {
                dp2[i][j] = group[j] + dp2[i][j - 1];
                if (dp1[i][j] >= minProfit && dp2[i][j] < n) {
                    count++;
                }
            }
        }
        return count;
    }


    //背包问题  0、1
    public int zeroOnePackExecutor1st(int N, int V, int[] v, int[] w) {
        int[] dp = new int[V + 1];
        //初始状态
        for (int i = 0; i < V + 1; i++) {
            dp[i] = 0;
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                dp[j] = Math.max(dp[j], j - v[i - 1] < 0 ? 0 : dp[j - v[i - 1]] + w[i - 1]);
            }
        }
        return dp[V];
    }

    /**
     * https://leetcode-cn.com/problems/coin-change/
     * 完全背包
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = amount + 1;
        }
        dp[0] = 0;
        /*for (int i = 0; i < coins.length+1; i++) {
            dp[i][0] = 0;
        }*/
        for (int i = 1; i < coins.length + 1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j < coins[i - 1]) {
                    dp[j] = dp[j];
                } else {
                    dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public int 多重背包(int[] volume, int[] value, int[] count, int maxVolume) {
        List<Integer> newV = new ArrayList<>();
        List<Integer> newW = new ArrayList<>();
        //用二进制优化  将对应的数据通过二进制拆为多个，再乘以各自的价值和体积放入数组
        for (int i = 0; i < count.length; i++) {
            int temp = count[i];
            for (int j = 1; j <= temp; j <<= 1) {
                newV.add(j * volume[i]); //存体积
                newW.add(j * value[i]); //存价值
            }
        }
        int[] VV = new int[newV.size()];
        for (int i = 0; i < newV.size(); i++) {
            VV[i] = newV.get(i);
        }
        int[] WW = new int[newW.size()];
        for (int i = 0; i < newW.size(); i++) {
            WW[i] = newW.get(i);
        }
        //转化为01背包问题
       return zeroOnePackExecutor1st(VV.length,maxVolume,VV,WW);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] i = {1, 2, 5};
        int[] obstacles = {2, 4, 4, 5};
        System.out.println(solution.coinChange(i, 11));
    }


}
