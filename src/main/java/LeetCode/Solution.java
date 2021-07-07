package LeetCode;

import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

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
        dp[0] = 0;
        /*for (int i = 0; i < coins.length+1; i++) {
            dp[i][0] = 0;
        }*/
        for (int i = 1; i < N + 1; i++) {
            for (int j = v[i - 1]; j < V + 1; j++) {
                dp[j] = Math.max(dp[j], dp[j - v[i - 1]] + w[i - 1]);
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
        return zeroOnePackExecutor1st(VV.length, maxVolume, VV, WW);
    }

    /**
     * https://leetcode-cn.com/problems/top-k-frequent-words/
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<String> strings = new ArrayList<>(map.keySet());
        Collections.sort(strings, (o1, o2) -> map.get(o1).equals(map.get(o2)) ? o1.compareTo(o2) : map.get(o2) - (map.get(o1)));
        return strings.subList(0, k);
    }

    /**
     * https://leetcode-cn.com/problems/binary-number-with-alternating-bits/
     *
     * @param n
     * @return
     */
    public boolean hasAlternatingBits(int n) {
        char[] chars = Integer.toBinaryString(n).toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/max-area-of-island/
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] flag = new boolean[grid.length][grid[0].length];
        int max = 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!flag[i][j] && grid[i][j] == 1) {
                    max = Math.max(doMaxAreaOfIsland(grid, i, j, count, flag), max);
                    count = 0;
                } else {
                    flag[i][j] = true;
                }
            }
        }
        return max;
    }

    public int doMaxAreaOfIsland(int[][] grid, int x, int y, int max, boolean[][] flag) {
        if (grid[x][y] != 0) {
            flag[x][y] = true;
            ++max;
            if (x + 1 < grid.length && !flag[x + 1][y]) {
                max = doMaxAreaOfIsland(grid, x + 1, y, max, flag);
            }
            if (x - 1 >= 0 && !flag[x - 1][y]) {
                max = doMaxAreaOfIsland(grid, x - 1, y, max, flag);
            }
            if (y + 1 < grid[x].length && !flag[x][y + 1]) {
                max = doMaxAreaOfIsland(grid, x, y + 1, max, flag);
            }
            if (y - 1 >= 0 && !flag[x][y - 1]) {
                max = doMaxAreaOfIsland(grid, x, y - 1, max, flag);
            }
        }
        return max;
    }

    public int kthLargestValue(int[][] matrix, int k) {
        int[][] max = new int[matrix.length][matrix[0].length];
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                max[i][j] = matrix[i][j];
                if (i - 1 >= 0) {
                    max[i][j] = max[i][j] ^ max[i - 1][j];
                }
                if (j - 1 >= 0) {
                    max[i][j] = max[i][j] ^ max[i][j - 1];
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    max[i][j] = max[i][j] ^ max[i - 1][j - 1];
                }
                integers.add(max[i][j]);
            }
        }

        Collections.sort(integers);
        return integers.get(integers.size() - k);
    }

    /**
     * https://leetcode-cn.com/problems/uncrossed-lines/
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int nums1Length = nums1.length + 1;
        int nums2Length = nums2.length + 1;
        int[][] flag = new int[nums1Length][nums2Length];
        for (int i = 0; i < nums1Length; i++) {
            flag[i][0] = 0;
        }
        for (int i = 0; i < nums2Length; i++) {
            flag[0][i] = 0;
        }
        for (int i = 1; i < nums1Length; i++) {
            for (int j = 1; j < nums2Length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    flag[i][j] = 1;
                    flag[i][j] += flag[i - 1][j - 1];
                } else {
                    flag[i][j] = Math.max(flag[i - 1][j], flag[i][j - 1]);
                }
            }
        }
        return flag[nums1Length - 1][nums2Length - 1];
    }

    /**
     * https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
     *
     * @param arr
     * @return
     */
    public int countTriplets(int[] arr) {
        int count = 0;
        int dp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (i == j) {
                    dp = arr[j];
                } else {
                    dp = dp ^ arr[j];
                    if (dp == 0) {
                        count += (j - i);
                    }
                }
            }
        }
        return count;
    }

    /**
     * https://leetcode-cn.com/problems/strange-printer/
     */
    public int strangePrinter(String s) {
        char[] chars = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, dp[i][k] + dp[k + 1][j]);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][s.length() - 1];
    }


    /**
     * https://leetcode-cn.com/problems/non-decreasing-array/
     *
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i + 1 < nums.length && nums[i] > nums[i + 1]) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
            if (i - 1 > 0 && i + 1 < nums.length && nums[i - 1] == nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/beautiful-arrangement-ii/
     *
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray(int n, int k) {
        int[] a = new int[n];
        a[0] = 1;
        int count = 1;
        int j = -1;
        for (int i = k; i > 0; i--) {
            a[count] = Math.abs(i - a[count - 1] * j);
            j = -j;
            count++;
        }
        for (int i = a[1] + 1; i <= n; i++) {
            a[count] = i;
            count++;
        }
        return a;
    }

    /**
     * https://leetcode-cn.com/problems/make-the-xor-of-all-segments-equal-to-zero/
     * 答案未完成
     *
     * @param nums
     * @param k
     * @return
     */
    public int minChanges(int[] nums, int k) {
        int count = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            int temp = i;
            while (temp < nums.length - k) {
                Integer orDefault = map.getOrDefault(nums[temp], 0);
                orDefault++;
                map.put(nums[temp], orDefault);
                temp = temp + k;
            }
            Integer max = map.keySet().stream().max(((a, b) -> {
                if (map.get(a) > map.get(b)) {
                    return 1;
                } else return -1;
            })).get();
            for (Integer integer : map.keySet()) {
                if (max.equals(integer)) {
                    min = Math.min(min, max);
                } else {
                    count += map.get(integer);
                }
            }
        }
        return count;
    }

    /**
     * https://leetcode-cn.com/problems/hamming-distance/
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int i = x ^ y;
        int count = 0;
        while (i > 0) {
            i &= (i - 1);
            count++;
        }
        return count;
    }


    /**
     * https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements-ii/
     *
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        long min = Integer.MAX_VALUE;
        long one = 0;
        for (int i = 0; i < nums.length; i++) {
            one = 0;
            for (int j = 0; j < nums.length; j++) {
                one = one + Math.abs(nums[i] - nums[j]);
            }
            min = Math.min(min, one);
        }
        return (int) min;
    }

    /**
     * https://leetcode-cn.com/problems/total-hamming-distance/
     *
     * @param nums
     * @return
     */
    public int totalHammingDistance(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int i1 = nums[i] ^ nums[j];
                while (i1 > 0) {
                    i1 &= (i1 - 1);
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * https://leetcode-cn.com/problems/integer-break/
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                max = Math.max(max, Math.max((i - j) * j, dp[i - j] * j));
            }
            dp[i] = max;
        }
        return dp[n];
    }

    /**
     * https://leetcode-cn.com/problems/reverse-string/
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int i1 = s.length - 1;
        for (int i = 0; i < (s.length / 2); i++) {
            char temp = s[i];
            s[i] = s[i1 - i];
            s[i1 - i] = temp;
        }
    }

    /**
     * https://leetcode-cn.com/problems/top-k-frequent-elements/
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        ArrayList<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        //已经拿到了个个数出现的频率，只需要进行排序就可以拿出钱个数据
        Map.Entry<Integer, Integer>[] b = new Map.Entry[entries.size()];
        for (int i = 0; i < entries.size(); i++) {
            b[i] = entries.get(i);
        }
        querySort(b, 0, b.length - 1, k);
        List<Map.Entry<Integer, Integer>> entries1 = Arrays.asList(b).subList(b.length - k, b.length);
        List<Integer> collect = entries1.stream().map(Map.Entry::getKey).collect(Collectors.toList());
        int[] re = new int[collect.size()];
        for (int i = 0; i < collect.size(); i++) {
            re[i] = collect.get(i);
        }
        return re;
    }

    public void querySort(Map.Entry<Integer, Integer>[] nums, int i, int j, int k) {
        int l = i;
        int r = j;
        if (l >= r || nums.length - 1 - j > k) {
            return;
        }
        int temp = nums[l].getValue();
        Map.Entry<Integer, Integer> num = nums[l];
        while (r > l) {
            while (r > l && nums[r].getValue() >= temp) {
                r--;
            }
            if (r > l) {
                nums[l] = nums[r];
                l++;
            }
            while (r > l && temp > nums[l].getValue()) {
                l++;
            }
            if (r > l) {
                nums[r] = nums[l];
                r--;
            }
        }
        nums[r] = num;
        querySort(nums, i, r - 1, k);
        querySort(nums, r + 1, j, k);
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        /*int[] temp = new int[nums.length];
        temp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            temp[i] = temp[i - 1] + nums[i];
            if (temp[i] % k == 0){
                return true;
            }
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                temp[j] = temp[j] - temp[i-1];
                if (temp[j] % k == 0){
                    return true;
                }
            }
            temp[i] = nums[i];
        }
        return false;*/

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % k;
            if (map.containsKey(sum)) {
                Integer integer = map.get(sum);
                if (i - integer > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }

    /**
     * https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=188&&tqId=38547&rp=1&ru=/activity/oj&qru=/ta/job-code-high-week/question-ranking
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode next = head.next;
        if (next == null) {
            return head;
        }
        head.next = null;
        while (next.next != null) {
            ListNode temp = next.next;
            next.next = head;
            head = next;
            next = temp;
        }
        next.next = head;
        return next;
    }

    /**
     * https://www.nowcoder.com/practice/e3769a5f49894d49b871c09cadd13a61?tpId=188&tqId=38547&rp=1&ru=%2Factivity%2Foj&qru=%2Fta%2Fjob-code-high-week%2Fquestion-ranking
     *
     * @param operators
     * @param k
     * @return
     */
    public int[] LRU(int[][] operators, int k) {
        List<Integer> re = new ArrayList();
        LinkedList<Integer> list = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < operators.length; i++) {
            int[] operator = operators[i];
            int i1 = operator[0];
            if (i1 == 1) {
                if (list.size() >= k) {
                    Integer integer = list.removeFirst();
                    map.remove(integer);
                }
                map.put(operator[1], operator[2]);
                list.addLast(operator[1]);
            } else if (i1 == 2) {
                Integer integer = map.get(operator[1]);
                if (integer == null) {
                    re.add(-1);
                } else {
                    re.add(integer);
                    list.remove(list.indexOf(operator[1]));
                    list.addLast(operator[1]);
                }
            }
        }
        int[] ints = new int[re.size()];
        for (int i = 0; i < re.size(); i++) {
            ints[i] = re.get(i);
        }
        return ints;
    }


    /**
     * nowcoder.com/practice/650474f313294468a4ded3ce0f7898b9?tpId=188&tqId=38547&rp=1&ru=%2Factivity%2Foj&qru=%2Fta%2Fjob-code-high-week%2Fquestion-ranking
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        List<ListNode> list = new ArrayList<>();
        list.add(head);
        while (head.next != null) {
            head = head.next;
            if (list.contains(head)) {
                return true;
            }
            list.add(head);
        }
        return false;
    }


    public int search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        Integer max = null;
        while (left <= right) {
            mid = left + ((right - left) / 2);
            if (nums[mid] == target) {
                while (mid > 0) {
                    mid--;
                    if (nums[mid] != target) {
                        return mid + 1;
                    }
                }
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return max == null ? -1 : max;
    }

    /**
     * https://www.nowcoder.com/practice/04a5560e43e24e9db4595865dc9c63a3?tpId=188&tqId=38547&rp=1&ru=%2Factivity%2Foj&qru=%2Fta%2Fjob-code-high-week%2Fquestion-ranking
     *
     * @param root
     * @return
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        doLevelOrder(root, 0, arrayList);
        return arrayList;
    }

    public void doLevelOrder(TreeNode treeNode, int leve, ArrayList<ArrayList<Integer>> arrayList) {
        Integer integers = arrayList.size();
        if (leve >= integers) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(treeNode.val);
            arrayList.add(leve, list);
        } else {
            ArrayList<Integer> integers1 = arrayList.get(leve);
            integers1.add(treeNode.val);
            arrayList.set(leve, integers1);
        }
        if (treeNode.right != null) {
            doLevelOrder(treeNode.right, leve + 1, arrayList);
        }
        if (treeNode.left != null) {
            doLevelOrder(treeNode.left, leve + 1, arrayList);
        }
    }

    /**
     * https://www.nowcoder.com/practice/b56799ebfd684fb394bd315e89324fb4?tpId=188&tags=&title=&difficulty=0&judgeStatus=0&rp=1
     *
     * @param arr
     * @return
     */
    public int maxLength(int[] arr) {
        int j = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        while (j < arr.length) {
            if (map.keySet().contains(arr[j])) {
                max = Math.max(max, map.keySet().size());
                j = map.get(arr[j]) + 1;
                map.clear();
            } else {
                map.put(arr[j], j);
                j++;
            }
        }
        max = Math.max(max, map.keySet().size());
        return max;
    }

    /**
     * https://www.nowcoder.com/practice/e0cc33a83afe4530bcec46eba3325116?tpId=188&tags=&title=&difficulty=0&judgeStatus=0&rp=1
     *
     * @param root
     * @param o1
     * @param o2
     * @return
     */
    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        doLowestCommonAncestor(root, o1, o2, new ArrayList<>(), map);
        List<Integer> list = map.get(o1);
        List<Integer> list1 = map.get(o2);

        for (int i = 0; i < list.size() && i < list1.size(); i++) {
            if (!list.get(i).equals(list1.get(i))) {
                return list.get(i - 1);
            }
        }
        return 0;
    }

    public void doLowestCommonAncestor(TreeNode root, int o1, int o2, List<Integer> list, Map<Integer, List<Integer>> map) {
        if (root == null) {
            return;
        } else if (root.val == o1 || root.val == o2) {
            list.add(root.val);
            map.put(root.val, list);
            return;
        } else {
            list.add(root.val);
        }
        doLowestCommonAncestor(root.right, o1, o2, new ArrayList<>(list), map);
        doLowestCommonAncestor(root.left, o1, o2, new ArrayList<>(list), map);
    }

    /**
     * https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=188&tags=&title=&difficulty=0&judgeStatus=0&rp=1
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (k > input.length || input.length == 0) {
            return new ArrayList<>();
        }
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(input[0]);
        Stack<Integer> stack2 = new Stack<>();
        for (int i = 1; i < input.length; i++) {
            int size = stack1.size();
            for (int j = 0; j < size; j++) {
                Integer pop = stack1.pop();
                if (pop > input[i]) {
                    stack2.push(pop);
                } else {
                    stack1.push(pop);
                    break;
                }
            }
            if (stack1.size() < k) {
                stack1.push(input[i]);
            }
            int size1 = stack2.size();
            for (int j = 0; j < size1; j++) {
                if (stack1.size() < k) {
                    stack1.push(stack2.pop());
                }
            }
            stack2.clear();
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(stack1.pop());
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * https://www.nowcoder.com/practice/89865d4375634fc484f3a24b7fe65665?tpId=188&tags=&title=&difficulty=0&judgeStatus=0&rp=1
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int A[], int m, int B[], int n) {
        List<Integer> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (A[i] > B[j]) {
                list.add(B[j]);
                j++;
            } else {
                list.add(A[i]);
                i++;
            }
        }
        for (; i < m; i++) {
            list.add(A[i]);
        }
        for (; j < n; j++) {
            list.add(B[j]);
        }
        for (int k = 0; k < list.size(); k++) {
            A[k] = list.get(k);
        }
    }

    /**
     * https://www.nowcoder.com/practice/a9fec6c46a684ad5a3abd4e365a9d362?tpId=188&tags=&title=&difficulty=0&judgeStatus=0&rp=1
     *
     * @param root
     * @return
     */
    public int[][] threeOrders(TreeNode root) {
        int[][] re = new int[3][];
        List<Integer> list = new ArrayList<>();
        lmr(root, list);
        int[] lmr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            lmr[i] = list.get(i);
        }
        re[0] = lmr;
        list.clear();

        mlr(root, list);
        lmr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            lmr[i] = list.get(i);
        }
        re[1] = lmr;
        list.clear();


        lrm(root, list);
        lmr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            lmr[i] = list.get(i);
        }
        re[2] = lmr;
        list.clear();
        return re;
    }

    public void lmr(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        lmr(root.left, list);
        list.add(root.val);
        lmr(root.right, list);
    }

    public void mlr(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        mlr(root.left, list);
        mlr(root.right, list);
    }

    public void lrm(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        lrm(root.left, list);
        lrm(root.right, list);
        list.add(root.val);
    }


    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        //dp[i][0]  有  dp[i][1] 无
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }

    /**
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int[] newNums = new int[nums.length];
        for (int j = 0; j < nums.length; j++) {
            int x = j + k;
            while (x > nums.length - 1) {
                x -= nums.length;
            }
            newNums[x] = nums[j];
        }
        nums = newNums;
    }

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x248f5/
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x21ib6/
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                count = 0;
            } else {
                count++;
            }
            if (count == 2) {
                return nums[i];
            }
        }
        if (nums[nums.length - 2] != nums[nums.length - 1]) {
            return nums[nums.length - 1];
        }
        return 0;
    }


    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n1 = 0;
        int n2 = 0;
        List<Integer> list = new ArrayList<>();
        while (n1 < nums1.length && n2 < nums2.length) {
            if (nums1[n1] == nums2[n2]) {
                list.add(nums1[n1]);
                n1++;
                n2++;
            } else {
                if (nums1[n1] > nums2[n2]) {
                    n2++;
                } else {
                    n1++;
                }
            }
        }
        int[] re = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            re[i] = list.get(i);
        }
        return re;
    }

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2cv1c/
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack1 = new Stack<>();
        for (int digit : digits) {
            stack.push(digit);
        }
        int sum = 1;
        while (true) {
            Integer pop = stack.pop();
            int i = pop + sum;
            if (i < 10) {
                sum = 0;
            }
            stack1.push(i % 10);
            if (stack.size() == 0) {
                if (i >= 10) {
                    stack1.push(1);
                }
                break;
            }
        }
        int size = stack1.size();
        int[] re = new int[size];
        for (int i = 0; i < size; i++) {
            re[i] = stack1.pop();
        }
        return re;
    }

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2ba4i/
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] != 0) {
                        nums[i] = nums[j];
                        nums[j] = 0;
                        break;
                    }
                    if (j == nums.length - 1) {
                        return;
                    }
                }
            }
        }
    }

//    /**
//     *https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2f9gg/
//     * @param board
//     * @return
//     */
//    public boolean isValidSudoku(char[][] board) {
//        Map<Character,List<int[]>> map = new HashMap<>();
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                List<int[]> orDefault = map.getOrDefault(board[i][j], new ArrayList<>());
//                int[] ints = new int[2];
//                ints[0] = i;
//                ints[1] = j;
//                orDefault.add(ints);
//                map.put(board[i][j],orDefault);
//            }
//        }
//
//        Set<Character> characters = map.keySet();
//        for (Character character : characters) {
//            if (characters.equals('.')){
//                continue;
//            }
//            List<int[]> list = map.get(character);
//            for (int i = 0; i < list.size() - 1; i++) {
//
//            }
//        }
//    }


    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnhhkv/
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        //因为是对称的，只需要计算循环前半行即可
        for (int i = 0; i < length / 2; i++)
            for (int j = i; j < length - i - 1; j++) {
                int temp = matrix[i][j];
                int m = length - j - 1;
                int n = length - i - 1;
                matrix[i][j] = matrix[m][i];
                matrix[m][i] = matrix[n][m];
                matrix[n][m] = matrix[j][n];
                matrix[j][n] = temp;
            }

    }

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnx13t/
     *
     * @param x
     * @return
     */

    public int reverse(int x) {
        long re = 0;
        while (x != 0) {
            re = re * 10 + x % 10;
            x = x / 10;
        }
        return (int) re == re ? (int) re : 0;
    }

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn5z8r/
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Character character : map.keySet()) {
            if (map.get(character) == 1) {
                min = Math.min(min, s.indexOf(character));
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn96us/
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> maps = new HashMap<>();

        for (char c : s.toCharArray()) {
            maps.put(c, maps.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (!maps.containsKey(c)) {
                return false;
            }
            maps.put(c, maps.getOrDefault(c, 0) - 1);
        }

        for (Character character : maps.keySet()) {
            if (maps.get(character) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xne8id/
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
                left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
                right--;
            if (chars[left] == chars[right]) {
                left++;
                right++;
            } else {
                return false;
            }
        }
        return false;
    }


    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnr003/
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (haystack.equals(needle)) {
            return 0;
        }
        for (int i = 0; i < haystack.length() - needle.length() - 1; i++) {
            if (haystack.startsWith(needle, i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnpvdm/
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        if (n == 2) {
            return "11";
        }
        String s = countAndSay(n - 1);
        StringBuilder stringBuilder = new StringBuilder();
        int x = 0;
        int j = 0;
        while (x < s.length()) {
            while (j < s.length() && s.charAt(x) == s.charAt(j)) {
                j++;
            }
            stringBuilder.append(j - x).append(s.charAt(x));
            x = j;
        }
        return stringBuilder.toString();
    }


    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnmav1/
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        int min = Integer.MAX_VALUE;
        String minStr = "";
        for (String str : strs) {
            if (str.length() < min) {
                min = str.length();
                minStr = str;
            }
        }

        for (String str : strs) {
            while (!str.startsWith(minStr)) {
                minStr = minStr.substring(0, minStr.length() - 1);
            }
            if (minStr.length() == 0) {
                return "";
            }
        }
        return minStr;
    }


    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnarn7/
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn2925/
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 1;
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            count++;
        }
        int i = count - n;
        if (i == 0) {
            return head.next;
        }
        temp = head;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        temp.val = temp.next.val;
        temp.next = temp.next.next;
        return head;
    }

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnnhm6/
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        /*if(head==null){
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        head = stack.pop();
        ListNode re = head;
        while (stack.size() != 0) {
            re.next = stack.pop();
            re = re.next;
        }
        re.next = null;
        return head;*/
        if (head == null) {
            return null;
        }
        ListNode re = head;
        while (re.next != null) {
            re = re.next;
        }
        ListNode listNode = doReverseList1(head);
        listNode.next = null;
        return re;
    }

    public ListNode doReverseList1(ListNode head) {
        if (head.next == null) {
            return head;
        }
        doReverseList1(head.next).next = head;
        return head;
    }


    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnnbp2/
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(0);
        ListNode nNode = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                nNode.next = l1;
                l1 = l1.next;
            } else if (l1.val > l2.val) {
                nNode.next = l2;
                l2 = l2.next;
            } else {
                nNode.next = l1;
                l1 = l1.next;
                nNode = nNode.next;
                nNode.next = l2;
                l2 = l2.next;
            }
            nNode = nNode.next;
        }

        while (l1 != null) {
            nNode.next = l1;
            nNode = nNode.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            nNode.next = l2;
            nNode = nNode.next;
            l2 = l2.next;
        }
        return head.next;
    }

    /**
     * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnwzei/
     *
     * @param head
     * @return
     */
    public boolean hasCycle1(ListNode head) {
        ListNode listNode = head;
        ListNode listNode1 = head.next;

        while (listNode1 != null && listNode1.next != null && listNode1.next.next != null) {
            if (listNode1 == listNode) {
                return true;
            }
            listNode = listNode.next;
            listNode1 = listNode1.next.next;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] ints = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}
        };
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(1);
        treeNode.right = treeNode1;
        treeNode.left = treeNode2;
        String[] strs = {"flower", "flow", "flight"};
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode1;
        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(2);
        ListNode listNode6 = new ListNode(3);
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        System.out.println(solution.hasCycle1(listNode));
    }
}

//public class Main {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int num = scan.nextInt();
//        int re = 0;
//        int max = 9;
//        int le = 1;
//        if (num > 45) {
//            System.out.println(-1
//            );
//        }
//        while (num > 0) {
//            if (num > le) {
//                re += re + max * le;
//                le *= 10;
//            } else {
//                re += re + max * num;
//            }
//            num -= max;
//            max--;
//        }
//        System.out.println(re);
//    }
//}


//public class Main {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int num = scan.nextInt();
//        scan = new Scanner(System.in);
//        String s = scan.nextLine();
//        scan = new Scanner(System.in);
//        String s1 = scan.nextLine();
//        int max = 0;
//        List<Character> chars1 = s.chars()
//                .mapToObj(i -> (char) i)
//                .sorted(Character::compareTo)
//                .collect(Collectors.toList());
//        List<Character> chars = s1.chars()
//                .mapToObj(i -> (char) i)
//                .sorted(Character::compareTo)
//                .collect(Collectors.toList());
//        for (int i = 0; i < num; i++) {
//            if (chars1.get(i) != chars.get(i)) {
//                max += Math.abs(chars1.get(i) - chars.get(i));
//            }
//        }
//        System.out.println(max);
//    }
//}


//public class Main {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int N = scan.nextInt(), M = scan.nextInt(), count = 0;
//        int[] arr = new int[N];
//        for(int i = 0 ; i < N ; i++){
//            int num = scan.nextInt();
//            if(num >= M)arr[i] = num % M;
//            else arr[i] = M;
//        }
//        Map<Integer,Integer> list = new HashMap<>();
//        int sum = 0;
//        for (int i = 0; i < N; i++) {
//            sum = (sum+arr[i])%M;
//            if(list.containsKey(sum)){
//                count += list.get(sum);
//            }
//            list.put(sum,list.getOrDefault(sum,0)+1);
//        }
//        System.out.println(count);
//    }
//}

//public class Main {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int N = scan.nextInt();
//        int M = scan.nextInt();
//        int[][] re = new int[N][M];
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                int num = scan.nextInt();
//                re[i][j] = num;
//            }
//        }
//        int count = 0;
//        for (int j = 0; j < N; j++) {
//            for (int k = j + 1; k < N; k++) {
//                int x = re[j][0] +re[k][0];
//                boolean flag = true;
//                for (int i = 1; i < M; i++) {
//                     if (x != (re[j][i] +re[k][i])){
//                         flag = false;
//                         break;
//                     }
//                }
//                if (flag){
//                    count++;
//                }
//            }
//        }
//        System.out.println(count);
//    }
//
//}

//public class Main {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int N = scan.nextInt();
//        int[][] re = new int[N][3];
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < 3; j++) {
//                re[i][j] = scan.nextInt();
//            }
//        }
//
//        for (int i = 0; i < N; i++) {
//            int A = re[i][0];
//            int B = re[i][1];
//            int n = re[i][2];
//            long h2 =A;
//            long h1 =A * A - 2 * B;
//            if (n == 2) {
//                System.out.println(h1);
//                continue;
//            }
//            for (int j = 3; j <= n; j++) {
//                long temp = h1;
//                h1 = A*h1 - B*h2;
//                h2 = temp;
//            }
//            System.out.println(h1);
//        }
//    }
//}

//class Main {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int N = scan.nextInt();
//        String[] strings = new String[N + 1];
//        for (int i = 0; i < N + 1; i++) {
//            strings[i] = scan.nextLine();
//        }
//        for (int i = 1; i < N + 1; i++) {
//            char[] chars = strings[i].toCharArray();
//            boolean flag1 = false;
//            boolean flag2 = false;
//            List<Character> list = new ArrayList<>();
//            list.add(chars[0]);
//            for (int j = 1; j < chars.length; j++) {
//                boolean tem = flag1;
//                if (chars[j - 1] == chars[j]) {
//                    if (tem || (flag2 && j > 2)) {
//                        continue;
//                    }
//                    flag1 = true;
//                } else {
//                    flag1 = false;
//                }
//                list.add(chars[j]);
//
//                flag2 = tem;
//            }
//            char[] re = new char[list.size()];
//            for (int j = 0; j < list.size(); j++) {
//                re[j] = list.get(j);
//            }
//            System.out.println(String.valueOf(re));
//        }
//    }
//}


//class Main {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int N = scan.nextInt(), M = scan.nextInt();
//        int[] nums = new int[N];
//        for (int i = 0; i < N; i++) {
//            nums[i] = scan.nextInt();
//        }
//
//        int[] cha = new int[N - 1];
//        for (int i = 0; i < N - 1; i++) {
//            cha[i] = nums[i + 1] - nums[i];
//        }
//        Set<String> strings = new HashSet<>();
//        for (int i = 0; i < cha.length; i++) {
//            int[] qian = new int[cha.length];
//            qian[i] = cha[i];
//            for (int j = 1; j < cha.length; j++) {
//                if (i == j) {
//                    continue;
//                }
//                qian[j] = cha[j] + qian[j - 1];
//                if (qian[j] <= M) {
//                    strings.add(Math.max(i, j) + "," + Math.min(i, j));
//                }
//            }
//        }
//        System.out.println(strings.size());
//    }
//}
