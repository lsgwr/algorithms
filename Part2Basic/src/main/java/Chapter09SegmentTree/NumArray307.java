/***********************************************************
 * @Description : LeetCode题目307
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/19 13:27
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09SegmentTree;

public class NumArray307 {
    private SegmentTree<Integer> segmentTree;

    public NumArray307(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            // 注意这里不能用System.arrayCopy()函数，因为data和nums的数据类型不同
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }
    }

    public void update(int i, int val) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null");
        }
        segmentTree.update(i, val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null");
        }
        return segmentTree.query(i, j);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray307 obj = new NumArray307(nums);
        System.out.println(obj.sumRange(0, 2));
        obj.update(1, 2);
        System.out.println(obj.sumRange(0, 2));
    }
}
