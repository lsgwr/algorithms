/***********************************************************
 * @Description : 93.复原IP地址
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 17:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T93_复原IP地址;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean validId(String ip) {
        if (ip.length() > 1 && ip.charAt(0) == '0') {
            return false;
        }
        if (ip.length() > 3 || Integer.parseInt(ip) > 255) {
            return false;
        }
        return true;
    }

    private String concatList(List<String> numList) {
        StringBuilder sb = new StringBuilder();
        for (String numStr : numList) {
            sb.append(numStr).append(".");
        }
        // 最后的一个.要去掉
        return sb.substring(0, sb.length() - 1);
    }

    // 把s分成四分，判断这四份组成的ip的合理性
    // 递归过程：不断取字符串的前几个字符，每出现一个合法的字符串就接着往下取
    private void findIp(String s, List<String> numList, List<String> result) {
        if (numList.size() == 4) {
            result.add(concatList(numList));
            // 当s字符串已经被分割成空时，分割完毕，退出本层递归即可
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            if (numList.size() == 3) {
                // 前面已经分成3份了，这里直接把剩下的作为IP即可
                i = s.length();
            }
            String tmp = s.substring(0, i);
            if (!validId(tmp)) {
                continue;
            }
            numList.add(tmp);
            // i往后的字符串
            findIp(s.substring(i), numList, result);
            // 退出上一层递归，就要从numList中删除最后一个num
            numList.remove(numList.size() - 1);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if ("".equals(s) || s.length() < 4 || s.length() > 12) {
            return result;
        }
        List<String> numList = new ArrayList<>();
        findIp(s, numList, result);
        return result;
    }
}
