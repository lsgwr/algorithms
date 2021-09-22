/***********************************************************
 * @Description : 并查集第3版：把联通分量用树表示，
 * union(p, q)时让层次(即rank)少的联通分量的根节点指向层次多的联通分量的根节点
 * (即pRoot作为子节点，qRoot作为父节点，子节点指向父节点，没有父节点直接用自己)，代码表示为：`parent[pRoot]=qRoot`
 *
 * 并查集第4版：在find时，把底部的节点进一步网上提到直接指向根节点，层数逼近两层~效率最高
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/3 7:51
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11UnionFind.Section6PathCompression;

import Chapter11UnionFind.UF;

public class UnionFind implements UF {

    /**
     * 记录每个节点在联通分量中的父节点
     */
    private int[] parent;

    /**
     * rank[i]表示节点i所在的联通分量树的层数/高度/深度
     */
    private int[] rank;

    public UnionFind(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            // 初始化时每个顶点的父节点都认为是自己
            parent[i] = i;
            // 初始时所有元素都是互不相连地，所以每个元素都是一个并查集，每个并查集只有一个元素,也就是一层
            rank[i] = 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            // p和q在一个联通分量内，不需要union了，直接退出
            return;
        }
        // 不在一个并查集内的话，只需要把两个根节点连接起来即可
        // 第5节：根据层数优化。下面按照两个并并查集的层数(rank[i])的大小决定谁连接谁(层数少地连接层数多地)
        if (rank[pRoot] < rank[qRoot]) { // p所在的并查集层数小于q所在的并查集层数，p指向q
            // p所在的并查集连接q所在的并查集，rank[root]取两者中层数较大地，并不需要维护rank
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) { // p所在的并查集层数大于q所在的并查集层数,q指向p
            // p所在的并查集连接q所在的并查集，rank[root]取两者中层数较大地，并不需要维护rank
            // q所在的并查集连接p所在的并查集
            parent[qRoot] = pRoot;
        } else { // p所在的并查集层数等于q所在的并查集层数,谁指向谁都行，这里选p指向q
            //当 rank[pRoot] = rank[qRoot];
            parent[pRoot] = qRoot;
            // 两个层级相等的并查集树根节点相连，层数一定增长1，所以把新的并查集层数+1
            rank[qRoot] += 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 获取元素i所属的联通分量的根节点，因为是树，所以查找的时间复杂度是O(logn)
     *
     * @param i 元素，即parent数组的下标，用来唯一标识一个元素，即parent数组的下标既是索引又是元素
     * @return i所属的联通分量的根节点
     */
    private int find(int i) {
        if (i < 0 || i >= parent.length) {
            throw new IllegalArgumentException("传入的索引超出了数组范围！");
        }
        // 当i的父节点是自己时说明达到了根节点
        while (parent[i] != i) {
            // 第6节：路径压缩
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
}