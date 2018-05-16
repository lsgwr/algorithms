/***********************************************************
 * @Description : 测试delte方法
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 22:43
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06BST;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
        Random random = new Random();

        int N = 10000;
        for (int i = 0; i < N; i++) {
            int key = random.nextInt(N);
            // key和value设置成一样地.
            // 注意这里因为key是随机产生地，所以可能会出现key相同覆盖的情况,
            // 因此所有的键合并起来一定是order数组的子集，所以下面的删除最后一定是0
            bst.insert(key, key);
        }

        // order存储的都是key
        Integer[] order = new Integer[N];
        for (int i = 0; i < N; i++) {
            order[i] = i;
        }
        // 打乱order数组
        BST.shuffle(order);

        for (int i = 0; i < N; i++) {
            if (bst.contain(order[i])) {
                bst.deleteNode(order[i]);
                System.out.println("After Remove " + order[i] + ", size = " + bst.size());
            }
        }

        System.out.println("bst size = " + bst.size());

    }
}
