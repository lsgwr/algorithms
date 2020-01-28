/***********************************************************
 * @Description : 15.三数之和，夹逼法
 * https://leetcode-cn.com/problems/3sum/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 17:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.数组.T15_三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3Sum
 * 先排序， 然后左右夹逼， 注意跳过重复的数
 * Time Complexity: O(n^2)， Space Complexity: O(1)
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        // 声明为final，防止下面对target修改而不自知
        final int target = 0;
        // 每次循环固定一个i，让j和k从两侧向中间夹逼，之所以-2是为了给j和k留出空间，三个数不至于有重合
        for (int i = 0; i < nums.length - 2; ++i) {
            // 跳过重复的i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            // 夹逼的核心条件即j和k还未相遇
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] < target) {
                    // 比目标和小，因为数组是升序，所以只能j向右移动
                    ++j;
                    // 跳过重复的j，必须用while，别用错成if!那样只能去重一个！
                    while (nums[j] == nums[j - 1] && j < k) {
                        ++j;
                    }
                } else if (nums[i] + nums[j] + nums[k] > target) {
                    // 比目标值大，因为数组是升序，所以只能k向左移动
                    --k;
                    // 跳过重复的k，必须用while，别用错成if，那样只能去重一个！
                    while (nums[k] == nums[k + 1] && j < k) {
                        --k;
                    }
                } else {
                    // 正好等于目标值了
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // 单独移动一个都会变成不等于target，所以直接同时向右移动j和向左移动k
                    ++j;
                    --k;
                    // 跳过重复的j，必须用while，别用错成if那样只能去重一个！
                    while (nums[j] == nums[j - 1] && j < k) {
                        ++j;
                    }
                    // 跳过重复的k，必须用while，别用错成if那样只能去重一个！
                    while (nums[k] == nums[k + 1] && j < k) {
                        --k;
                    }
                }
            }
        }
        return result;
    }
}
