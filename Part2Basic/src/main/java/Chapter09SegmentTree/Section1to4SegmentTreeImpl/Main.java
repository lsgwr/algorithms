/***********************************************************
 * @Description : 这个测试用例就是。Leetcode上的303号问题
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/19 12:25
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09SegmentTree.Section1to4SegmentTreeImpl;

public class Main {
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        // 用于求和的线段树，也可以自定义其他的merger，比如求乘法、除法、求幂等等
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });
        // 1.查看树
        System.out.println(segmentTree);
        // 2.查询指定区间的和。左右边界都是闭区间
        System.out.println(segmentTree.query(0, 2)); // -2+0+3
        System.out.println(segmentTree.query(2, 5)); // 3+(-5)+2+(-1)
        System.out.println(segmentTree.query(0, 5)); // -2+0+3+(-5)+2+(-1)
    }
}
/***
 * SegmentTree {
 *   dataSize=6,
 *   data=[-2, 0, 3, -5, 2, -1],
 *   treeSize=24,
 *   tree=[-3, 1, -4, -2, 3, -3, -1, -2, 0, null, null, -5, 2, null, null, null, null, null, null, null, null, null, null, null]
 * }
 * 1
 * -1
 * -3
 */
