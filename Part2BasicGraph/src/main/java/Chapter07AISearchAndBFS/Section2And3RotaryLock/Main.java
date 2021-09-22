/***********************************************************
 * @Description : 转盘锁问题测试，测试存在超时问题，经过测试是因为deadends存成了ArrayList,换成HashSet立马就合格了
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
        System.out.println(solution.path());

        //
        String[] deadends2 = {"8888"};
        String target2 = "0009";
        System.out.println(solution.openLock(deadends2, target2));
        System.out.println(solution.path());

        String[] deadends3 = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target3 = "888";
        System.out.println(solution.openLock(deadends3, target3));
        // 没有路径，所以为Null
        System.out.println(solution.path());
    }
}

/**
 * 输出结果如下：
 * <p>
 * 6
 * [0000, 1000, 1100, 1200, 1201, 1202, 0202]
 * 1
 * [0000, 0009]
 * -1
 * Exception in thread "main" java.lang.NullPointerException
 */
