/**
 * @description 农夫需要把狼、羊、菜和自己运到对岸去，需要满足如下限制条件
 * 1.只有农夫能够划船，而且船比较小，除农夫外每次只能运一样东西
 * 2.如果没有农夫看着，羊会偷吃菜，狼会吃羊
 * 请考虑一种方法，让农夫能安全地安排这些东西和他自己过河
 * <p/>
 * 基于状态压缩的BFS实现
 * 把当前岸的物体作为状态，同时记录农夫是往对岸去还是回来
 * @author : 梁山广(Laing Shan Guang)
 * @date : 2019-12-17 15:31
 * @email : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07AISearchAndBFS.Section4And5IQ;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class FarmerCrossRiver {

    /**
     * 岸边禁止留下的状态, 没有农夫看着，羊会偷吃菜，狼会吃羊
     */
    private int[] deadends = {120, 23};

    /**
     * 记录四个物体是否全都到对岸了，true表示在对岸，非true表明在本岸
     * 0->"农夫", 1->"狼", 2->"羊", 3->"菜"
     */
    private boolean[] all = new boolean[4];

    private Map<Integer, String> map = new HashMap<>();

    /**
     * 当前岸最多有4个物体，农夫的标识为0，所以最大数是0123即123，这里取1000吧，保险点
     */
    private boolean[] visited = new boolean[1000];

    /**
     * 记录当前岸物体状态的上一个状态
     */
    private int[] pre = new int[1000];

    public FarmerCrossRiver() {
        String[] all = {"农夫", "狼", "羊", "菜"};
        for (int i = 0; i < all.length; i++) {
            // 建立好映射关系
            map.put(i, all[i]);
        }
    }

    /**
     * 获取当前的岸边的物体的状态
     */
    private int getCur() {
        int cur = 0;
        // 第一个是农夫，为0，不用管
        for (int i = 1; i < all.length; i++) {
            if (all[i]) {
                continue;
            }
            cur += Math.pow(10, 3 - i);
        }
        return cur;
    }

    public void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        // 运输状态：农夫初始时肯定是带着羊往对岸(对应设置true)，此时本岸剩下狼和菜
        all[0] = true;
        all[2] = true;
        // 记录当前岸的状态，离开本岸记为负
        int init = -getCur();
        queue.add(init);
        while (!queue.isEmpty()) {
            // 获取上一步的步骤
            int cur = queue.remove();
            if (cur > 0) {
                // 大于0说明农夫回到了本岸
                // Todo:
            } else if (cur < 0) {
                // 小于0说明农夫在对岸，不会有等于0的，因为等于0说明本岸空了，就说明目标达成了
                // Todo:
            }
        }
    }
}
