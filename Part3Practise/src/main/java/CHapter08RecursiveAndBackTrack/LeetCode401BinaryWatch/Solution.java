/***********************************************************
 * @Description : 401.二进制手表
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/25 01:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package CHapter08RecursiveAndBackTrack.LeetCode401BinaryWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    private void getResult(int[] nums, int start, int k, List<Integer> curList, List<List<Integer>> result){
        if(curList.size() == k){
            result.add(new ArrayList(curList));
            return;
        }
        for(int i = start; i < nums.length; i++){
            curList.add(nums[i]);
            getResult(nums, i + 1, k, curList, result);
            curList.remove(curList.size() - 1);
        }
    }
    private List<String> trans(List<List<Integer>> result){
        List<String> list = new ArrayList<>();
        for(List<Integer> lightList : result){
            int[] flags = new int[10];
            for(int i = 0; i < 10; i++){
                if(lightList.contains(i)){
                    flags[i] = 1;
                }else {
                    flags[i] = 0;
                }
            }
            int hour = 8 * flags[0] + 4 * flags[1] + 2 * flags[2] + flags[3];
            int minute = 32 * flags[4] + 16 * flags[5] + 8 * flags[6] + 4 * flags[7] + 2 * flags[8] + flags[9];
            if(minute < 10){
                list.add(hour + ":0" + minute);
            }else{
                list.add(hour + ":" + minute);
            }

        }
        Collections.sort(list);
        return list;
    }
    // 组合问题
    public List<String> readBinaryWatch(int num) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        // 下标0~3的元素表示上面四个灯，下标4~9的元素表示下面6个灯
        int[] nums = {0,1,2,3,4,5,6,7,8,9};
        getResult(nums, 0, num, curList, result);
        return trans(result);
    }

    public static void main(String[] args) {
        int num = 2;
        System.out.println(new Solution().readBinaryWatch(2));
    }
}
