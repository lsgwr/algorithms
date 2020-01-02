/***********************************************************
 * @Description : 测试第2节和第3节两种不同实现的并查集
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/2 22:37
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11UnionFind.Section4TestUFAndOptimizeBySize;

import Chapter11UnionFind.UF;

public class Main {

    public static void main(String[] args) {
        // 并查集一共的节点数
        int size = 100000;
        // 要进行union操作次数
        int m = 10000;

        UF uf1 = new Chapter11UnionFind.Section2QuickFind.UnionFind(size);
        System.out.println("11.2节 基于id数组的UnionFind实现：" + TestUF.test(uf1, m) + "s");

        UF uf2 = new Chapter11UnionFind.Section3QuickUnion.UnionFind(size);
        System.out.println("11.3节 基于parent数组的UnionFind实现：" + TestUF.test(uf2, m) + "s");
    }
}
/**
 * 11.2节 基于ID数组的UnionFind实现：0.1826555s
 * 11.3节 基于parent数组的UnionFind实现：0.0016694s
 */