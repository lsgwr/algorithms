/***********************************************************
 * @Description : 31.下一个排列，解题思路如下：
 * 以nums=[0,5,4,3,2,1]为例，end表示数组结尾
 * 1.判断按照字典序有木有下一个，如果完全降序就没有下一个
 * 2.如何判断有木有下一个呢？只要存在nums[p] < nums[p+1]的升序结构，就有，而且我们应该从右往左找，一旦找到，因为这样才是真正下一个，在例子数组中是p=0,nums[0]=0
 * 3.当发现nums[p] < nums[p+1]的结构时，从在[p+1, end)中找到最接近nums[p]并且又大于nums[p]的数字，由于降序，从右往左遍历即可得到nums[c]，在例子数组中是c=1,nums[1]=5
 * 4.然后交换nums[p-1]与num[c](例子数组中即交换nums[0]和nums[1]，得到[1,5,4,3,2,0])，然后对[i, end)排序即可(例子中是[5,4,3,2,0]排序)，排序只需要首尾不停交换即可(结果是[0,2,3,4,5])，因为已经是降序.最终结果是[1,0,2,3,4,5]
 *
 * 上面说的很抽象，还是需要拿一些例子思考才行，比如[0,5,4,3,2,1]，下一个是[1,0,2,3,4,5]
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 19:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T31_下一个排列;

/**
 * Next Permutation
 * Time Complexity: O(n), Space Complexity: O(1)
 */
public class Solution {
    public void nextPermutation(int[] nums) {
        nextPermutation(nums, 0, nums.length);
    }

    private static boolean nextPermutation(int[] nums, int begin, int end) {
        // 从右向左查找，找到第一个违反了从后向前遍历时上升趋势的nums[p]，即第一个满足nums[p] < nums[p + 1]的p
        int p = end - 2;// nums[p] 就是倒数第二个元素，nums[p+1]就是倒数第一个元素
        while (p > -1 && nums[p] >= nums[p + 1]) {
            // 只要是上升趋势就一直往前移动p
            p--;
        }
        // 如果遍历完数组，即p=-1了，说明还是没找到满足nums[p] < nums[p + 1]的p
        // 那么这个数就是nums数组组成的数字中最大地了，根据题意，逆序下就是要返回地最小值了
        if (p == -1) {
            reverse(nums, begin, end);
            return false;
        }
        // 从右向左，找到第一个比nums[p]大地元素nums[c]
        int c = end - 1;
        while (c > 0 && nums[c] <= nums[p]) {
            c--;
        }
        // 交换nuns[c]和nums[p]
        swap(nums, p, c);
        // 把交换后的p位置往后的元素逆序，最终的nums就是我们想要的下一个排列
        reverse(nums, p + 1, end);
        return true;
    }

    /**
     * 交换指定数组下标i和下标j处的两个元素
     *
     * @param nums 原始数组
     * @param i    下标i
     * @param j    下标j
     */
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 反转数组指定区间[start end)内的元素
     *
     * @param nums  数组
     * @param begin 反转开始的坐标
     * @param end   反转结束的坐标，开区间
     */
    private static void reverse(int[] nums, int begin, int end) {
        while (begin < end) {
            swap(nums, begin++, --end);
        }
    }
}
