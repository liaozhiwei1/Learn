package LeetCode;

import java.util.*;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-31 11:51:47
 **/
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.test(6, 5, 898196L));
    }

    public int test(int n, int k,Long number ) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int l = (int) (number % 10);
            number = number / 10;
            if (map.containsKey(l)) {
                Integer integer = map.get(l) + 1;
                map.put(l, integer);
            } else {
                map.put(l, 1);
            }
        }
        for (Integer key :
                map.keySet()) {
            Integer integer = map.get(key);
            int count = 0;
            int temp = k - integer;
            if (temp <= 0) {
                return 0;
            }
            Integer min = key, max = key;
            while (min >= 0 || max < 10) {
                min--;
                max++;
                if (map.containsKey(max)) {
                    Integer integer1 = map.get(max);
                    if (temp - integer1 > 0) {
                        count = count + (max - key) * integer1;
                        temp = temp - integer1;
                    } else {
                        count = count + (max - key) * temp;
                        result.put(key, count);
                        break;
                    }
                }
                if (map.containsKey(min)) {
                    Integer integer1 = map.get(min);
                    if (temp - integer1 > 0) {
                        count = count + (key - min) * integer1;
                        temp = temp - integer1;
                    } else {
                        count = count + (key - min) * temp;
                        result.put(key, count);
                        break;
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (Integer key :
                result.keySet()) {
            min = Math.min(min, result.get(key));
        }
        return min;
    }
}
