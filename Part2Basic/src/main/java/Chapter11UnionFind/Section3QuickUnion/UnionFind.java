/***********************************************************
 * @Description : 并查集第2版：把联通分量用树表示，
 * union(p, q)时把p所在联通分量的根节点pRoot指向q所在联通分量的根节点qRoot
 * (即pRoot作为子节点，qRoot作为父节点，子节点指向父节点，没有父节点直接用自己)，代码表示为：`parent[pRoot]=qRoot`
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/2 20:06
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11UnionFind.Section3QuickUnion;

import Chapter11UnionFind.UF;

public class UnionFind implements UF {

    /**
     * 记录每个节点在联通分量中的父节点
     */
    private int[] parent;

    public UnionFind(int size) {
        this.parent = new int[size];
        for (int i = 0; i < parent.length; i++) {
            // 初始化时每个顶点的父节点都认为是自己
            parent[i] = i;
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
        // p所在联通分量的根节点指向q所在联通分量的根节点，这样两个联通分量就连接到一起了，p和q自然也就联通了
        parent[pRoot] = qRoot;
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
            i = parent[i];
        }
        return i;
    }
}