/***********************************************************
 * @Description : 转盘锁问题测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/11 17:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07AISearchAndBFS.Section2And3RotaryLock;

public class Main {
    public static void main(String[] args) {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        Solution solution = new Solution();
        System.out.println(solution.openLock(deadends, target));

        //
        String[] deadends2 = {"8888"};
        String target2 = "0009";
        System.out.println(solution.openLock(deadends2, target2));

        String[] deadends3 = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target3 = "888";
        System.out.println(solution.openLock(deadends3, target3));
    }
}

/**
 * 输出结果如下：
 * <p>
 * 6
 * 1
 * -1
 */
