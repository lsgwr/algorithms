package Chapter11UnionFind;

public interface UF {
    /**
     * 判断p和q是否是联通
     *
     * @param p 顶点p
     * @param q 顶点q
     * @return p和q是联通返回true，否则返回false
     */
    boolean isConnected(int p, int q);

    /**
     * 把定点p和q联通起来，等效于把p和q所在的联通分量连接到一起，称为一个联通分量
     *
     * @param p 顶点p
     * @param q 顶点q
     */
    void unionElements(int p, int q);

    /**
     * 获取并查集内的元素个数
     * @return  并查集内的元素个数
     */
    int getSize();
}
