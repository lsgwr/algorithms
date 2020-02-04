/***********************************************************
 * @Description : 通过先序和中序数组生成后序数组
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 23:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第3章_二叉树.通过先序和中序数组生成后序数组;

import java.util.HashMap;

public class Solution {
    public int[] getPostArray(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        int len = pre.length;
        int[] pos = new int[len];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < len; i++) {
            map.put(in[i], i);
        }
        setPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
        return pos;
    }

    /**
     * 从右往左一次填好后序数组s
     * si为后序数组s该填的位置
     * 返回值为s该填的下一个位置
     */
    public int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj, int[] s, int si, HashMap<Integer, Integer> map) {
        if (pi > pj) {
            return si;
        }
        s[si--] = p[pi];
        int i = map.get(p[pi]);
        si = setPos(p, pj - nj + i + 1, pj, n, i + 1, nj, s, si, map);
        return setPos(p, pi + 1, pi + i - ni, n, ni, i - 1, s, si, map);
    }
}
