/***********************************************************
 * @Description : 并查集第1版：使用id数组来记录每个元素所属的联通分量
 * id[p]=id[q]表明p和q属于同一个联通分量，此时find操作时间复杂度是O(1)，不能再高了，所以本节叫QuickFind
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/2 16:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11UnionFind.Section2QuickFind;

import Chapter11UnionFind.UF;

public class UnionFind implements UF {

    /**
     * 存储每个节点所在的联通分量id的数组
     */
    private int[] id;

    public UnionFind(int size) {
        this.id = new int[size];
        for (int i = 0; i < id.length; i++) {
            // 初始化时每个顶点的联通分量id都不同
            id[i] = i;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {

    }

    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 获取元素e所属的联通分量编号,因为直接在数组中取值，所以是O(1)级别的
     *
     * @param i 元素，即id数组的下标，用来唯一标识一个元素，即id数组的下标既是索引又是元素
     * @return e所属的联通分量编号
     */
    private int find(int i) {
        if (i < 0 || i >= id.length) {
            throw new IllegalArgumentException("传入的索引超出了数组范围！");
        }
        return id[i];
    }
}
