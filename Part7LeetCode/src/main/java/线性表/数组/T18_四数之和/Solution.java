/***********************************************************
 * @Description : 18.四数之和
 * https://leetcode-cn.com/problems/4sum/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 18:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.数组.T18_四数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 4Sum
 * 先排序， 然后左右夹逼
 * Time Complexity: O(n^3)， Space Complexity: O(1)
 */
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        final int N = nums.length;
        if (N < 4) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < N - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < N - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int k = j + 1;
                int l = N - 1;
                while (k < l) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum < target) {
                        k++;
                        while (nums[k] == nums[k - 1] && k < l) {
                            k++;
                        }
                    } else if (sum > target) {
                        l--;
                        while (nums[l] == nums[l + 1] && k < l) {
                            l--;
                        }
                    } else {
                        result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[l])));
                        k++;
                        l--;
                        while (nums[k] == nums[k - 1] && k < l) {
                            k++;
                        }
                        while (nums[l] == nums[l + 1] && k < l) {
                            l--;
                        }
                    }
                }
            }
        }
        return result;
    }
}
