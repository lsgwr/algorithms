# 02_Huffman树

## 1.[AcWing 148.果子合并](https://www.acwing.com/problem/content/150/)
### 联想：与`石子合并`题目进行比较
看起来和[石子合并](https://www.acwing.com/problem/content/284/)差不多，其实不同如下：
+ `石子合并`只能相邻两个合并
+ `果子合并`可以任意两个合并

> 因此前者只能用区间DP，后者也可以用DP，但是后者用DP肯定会超时，只能过`1/10`个用例，因此后者只能用贪心

### 算法解析
> (贪心,哈夫曼树,堆,优先队列) $O(nlogn)$

看成是一棵树，最后合并成一个节点。

完全二叉树，所有的叶子节点就是要合并的点。

内部点就是合并过来的点。

每个叶子节点会被算几次，看看这个叶子节点有几个父节点就会被算几次。

这道题的本质，`一共有多个完全二叉树，看看哪个完全二叉树的叶子节点的权值总和最小，把它输出来。`

这是经典哈夫曼树的模型，每次合并重量最小的两堆果子即可。直接用Java自带的优先队列即可

本题结合Huffman树的讲解为[贪心——Huffman树](https://www.jianshu.com/p/0d1cbb1f394b)

### 时间复杂度
> 使用小根堆维护所有果子，每次弹出堆顶的两堆果子，并将其合并，合并之后将两堆重量之和再次插入小根堆中。

每次操作会将果子的堆数减一，一共操作$n−1$次即可将所有果子合并成1堆。每次操作涉及到2次堆的删除操作和1次堆的插入操作，计算量是$O(logn)$。因此总时间复杂度是 $O(nlogn)$

### 代码实现
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> pq = new PriorityQueue<>();
        while(n-- > 0) {
            pq.add(sc.nextInt());
        }
        int res = 0;
        // 每次弹出堆中的两个最小值，合并后再压入回去
        while(pq.size() > 1){
            int m = pq.remove() + pq.remove(); // 弹出两个最小值进行合并
            pq.add(m); // 新结果加入
            res += m; // 代价累计
        }
        System.out.println(res);
    }
}
```