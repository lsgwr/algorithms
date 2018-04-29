/***********************************************************
 * @Description : 优化1：利用parent数组把上一节Union操作的效率由N降到logN
 *                优化2：通过比较两个并查集的大小来决定pRoot连接qRoot,  还是qRoot连接pRoot
 *                     (即谁是谁的父节点，元素少的并查集根节点连接元素多的并查集的根节点)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/29 23:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter6UnionFind.Section4QuickUnionOptimizedBySize;

public class UnionFind {
    /**
     * 数据之间连接的情况，这里是数组指针.parent[i]标是下标为i的元素的父元素下标
     */
    private int[] parent;
    /**
     * sz[i]表示以i为根的集合中元素的个数
     */
    private int[] sz;
    /**
     * 并查集有多少元素
     */
    private int count;

    UnionFind(int n) {
        count = n;
        // 初始化id数组
        parent = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; ++i) {
            // 初始化的时候每个元素的父元素指向自己.这个特征也是每个并查集的根节点的特征
            parent[i] = i;
            // 初始时所有元素都是互不相连地，所以每个元素都是一个并查集，每个并查集只有一个元素
            sz[i] = 1;
        }

    }

    /**
     * 返回p所在的根节点的下标(找到并查集的根节点)
     */
    private int find(int p) {
        assert (p >= 0 && p < count);
        // 只要节点不指向自身，说明还没到根节点
        while (p != parent[p]) {
            // 不断刷新p直接遇到并查集的根节点
            p = parent[p];
        }
        return p;
    }

    /**
     * 判断下标为p和q的点是否有同样的根，就可以判断p和q是否是连接在一起地了
     */
    boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 连接两个元素p和q
     */
    void unionElements(int p, int q) {
        // 找到p元素对应的根
        int pRoot = find(p);
        int qRoot = find(q);
        // 根元素相等说明两个元素已经在同一个并查集内了，是相互连接地了。直接返回
        if (pRoot == qRoot) {
            return;
        }
        // 不在一个并查集内的话，只需要把两个根节点连接起来即可
        // 下面按照两个并并查集的大小决定谁连接谁(元素少地连接元素多地)
        if (sz[pRoot] < sz[qRoot]) { // p所在的并查集元素数小于q所在的并查集元素数，p指向q
            // p所在的并查集连接q所在的并查集
            parent[pRoot] = qRoot;
            // 把q所在的并查集的元素书加上p所在的并查集的元素数
            sz[qRoot] += sz[pRoot];
        } else { // p所在的并查集元素数大于q所在的并查集元素数,q指向p
            // q所在的并查集连接p所在的并查集
            parent[qRoot] = pRoot;
            // 把p所在的并查集的元素书加上q所在的并查集的元素数
            sz[pRoot] += sz[qRoot];
        }
    }
}
