/***********************************************************
 * @Description : 220. 存在重复元素 III
 * https://leetcode-cn.com/problems/contains-duplicate-iii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 19:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.数组.T220_存在重复元素III;

import java.util.TreeSet;

/**
 * 对于数组内的一个整数， 如果能方便的求出大于它的最小整数和小于它的最大整数， 那么我们就可以判断
 * 差值是否大于 t 。 能方便的求出最大下限和最小上限， 最先想到的数据结构是二叉搜索树BST， 因为左孩
 * 子就是最大下限， 右孩子就是最小上限。
 * <p>
 * 可以用一个大小为k的滑动窗口， 将窗口内的元素组织成一个BST， 每次向前滑动一步， 添加一个新元素，
 * 同时删除一个最老的元素， 如此不断向前滑动， 不断更新BST。 如果BST内部有两个元素差值大于 t ， 则
 * 返回true， 如果直到扫描完数组， BST里都没有出现差值大于k的两个数， 则返回false。
 * 对于BST数据结构， 可以用现成的， C++里是 multiset ， Java里是 TreeSet
 */
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer floor = set.floor(num);
            Integer ceiling = set.ceiling(num);
            if ((floor != null && num <= floor + t) || (ceiling != null && num >= ceiling - t)) {
                return true;
            }
            set.add(num);
            if (i >= k) {
                // 维持宽度为k的窗口
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
