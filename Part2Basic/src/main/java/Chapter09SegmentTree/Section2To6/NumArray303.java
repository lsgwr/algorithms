/***********************************************************
 * @Description : LeetCode题目303
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/19 13:27
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09SegmentTree.Section2To6;

public class NumArray303 {
    private SegmentTree<Integer> segmentTree;

    public NumArray303(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            // 注意这里不能用System.arrayCopy()函数，因为data和nums的数据类型不同
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null");
        }
        return segmentTree.query(i, j);
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray303 obj = new NumArray303(nums);
        System.out.println(obj.sumRange(0, 2));
        System.out.println(obj.sumRange(2, 5));
        System.out.println(obj.sumRange(0, 5));
    }
}
