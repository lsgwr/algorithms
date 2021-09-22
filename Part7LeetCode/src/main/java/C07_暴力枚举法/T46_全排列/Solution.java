/***********************************************************
 * @Description : 46.全排列
 * https://leetcode-cn.com/problems/permutations/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 11:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C07_暴力枚举法.T46_全排列;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private void calPermutations(List<Integer> numList, List<Integer> curList, List<List<Integer>> result) {
        if (numList.size() == 0) {
            result.add(new ArrayList<>(curList));
            return;
        }
        for (int i = 0; i < numList.size(); i++) {
            curList.add(numList.get(i));
            List<Integer> numListNext = new ArrayList<>(numList);
            numListNext.remove(i);
            calPermutations(numListNext, curList, result);
            // 递归退出就删除一个元素
            curList.remove(curList.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        calPermutations(numList, curList, result);
        return result;
    }
}
