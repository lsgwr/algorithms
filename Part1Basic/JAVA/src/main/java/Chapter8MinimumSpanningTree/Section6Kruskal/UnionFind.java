/***********************************************************
 * @Description :  优化1：利用parent数组把上一节Union操作的效率由N降到logN
 *                 优化2：通过比较两个并查集的大小来决定pRoot连接qRoot,  还是qRoot连接pRoot
 *                      (即谁是谁的父节点，元素少的并查集根节点连接元素多的并查集的根节点)
 *                 优化3：按照两个并查集的层数来判断谁和谁连接，这样更准确，因为遍历的时候就是逐层遍历
 *                 优化4：find操作之前先把层数过多的并查集进行压缩，通过把尽量多的节点指向根节点来减少层数
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/29 23:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter8MinimumSpanningTree.Section6Kruskal;

public class UnionFind {
    /**
     * 数据之间连接的情况，这里是数组指针.parent[i]标是下标为i的元素的父元素下标
     */
    private int[] parent;
    /**
     * rank[i]表示以i为根的集合的层数
     */
    private int[] rank;
    /**
     * 并查集有多少元素
     */
    private int count;

    UnionFind(int n) {
        count = n;
        // 初始化id数组
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; ++i) {
            // 初始化的时候每个元素的父元素指向自己.这个特征也是每个并查集的根节点的特征
            parent[i] = i;
            // 初始时所有元素都是互不相连地，所以每个元素都是一个并查集，每个并查集只有一个元素,也就是一层
            rank[i] = 1;
        }

    }

    /**
     * 返回p所在的根节点的下标(找到并查集的根节点)
     */
    private int find(int p) {
        assert (p >= 0 && p < count);
        // 只要没到根节点就不断把节点上调,直到并查集只剩两层
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        // 这时候P还是子节点，但是因为成了两层，所以其父节点就是根节点。
        // 即使一开始就是根节点，但是因为根节点指向自己，所以返回parent[p]也是没问题的
        return parent[p];
    }

    /**
     * 判断下标为p和q的点是否有同样的根，就可以判断p和q是否是连接在一起地了
     */
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 连接两个元素p和q
     */
    public void unionElements(int p, int q) {
        // 找到p元素对应的根
        int pRoot = find(p);
        int qRoot = find(q);
        // 根元素相等说明两个元素已经在同一个并查集内了，是相互连接地了。直接返回
        if (pRoot == qRoot) {
            return;
        }
        // 不在一个并查集内的话，只需要把两个根节点连接起来即可
        // 下面按照两个并并查集的层数(rank[i])的大小决定谁连接谁(层数少地连接层数多地)
        if (rank[pRoot] < rank[qRoot]) { // p所在的并查集层数小于q所在的并查集层数，p指向q
            // p所在的并查集连接q所在的并查集，rank取两者中较大地，并不需要维护
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) { // p所在的并查集层数大于q所在的并查集层数,q指向p
            // q所在的并查集连接p所在的并查集
            parent[qRoot] = pRoot;
        } else { // p所在的并查集层数等于q所在的并查集层数,谁指向谁都行，这里选p指向q
            //当 rank[pRoot] = rank[qRoot];
            parent[pRoot] = qRoot;
            // 把新的并查集层数+1
            rank[qRoot] += 1;
        }
    }
}
