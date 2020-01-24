/***********************************************************
 * @Description : 77.组合问题
 * https://leetcode-cn.com/problems/combinations/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/24 22:22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package CHapter08RecursiveAndBackTrack.LeetCode77Combinations;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private void calCombinations(List<Integer> numList, List<Integer> curList, List<List<Integer>> result, int k) {
        if (curList.size() == k) {
            result.add(new ArrayList<>(curList));
            return;
        }
        for (int i = 0; i < numList.size(); i++) {
            curList.add(numList.get(i));
            List<Integer> numListNext = new ArrayList<>(numList.subList(i + 1, numList.size()));
            calCombinations(numListNext, curList, result, k);
            // 递归退出就删除一个元素
            curList.remove(curList.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numList.add(i + 1);
        }
        calCombinations(numList, curList, result, k);
        return result;
    }

    public static void main(String[] args) {
        int n = 4, k = 2;
        System.out.println(new Solution().combine(n, k));
    }
}
