/***********************************************************
 * @Description : 测试第2节、第3节、第5节三种不同实现的并查集
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/2 23:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11UnionFind.Section5OptimizeByRank;

import Chapter11UnionFind.Section4TestUF.TestUF;
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

        UF uf3 = new Chapter11UnionFind.Section5OptimizeByRank.UnionFind(size);
        System.out.println("11.5节 基于parent数组的UnionFind实现，根据树高度rank堆union操作进行了优化：" + TestUF.test(uf3, m) + "s");
    }
}
/**
 * size=100000，m=10000时，第3节和第5节基于parent数组实现的并查集性能优势很大：
 * 11.2节 基于id数组的UnionFind实现：0.1813127s
 * 11.3节 基于parent数组的UnionFind实现：0.0015811s
 * 11.5节 基于parent数组的UnionFind实现，根据树高度rank堆union操作进行了优化：0.0016426s
 *
 * size=100000，m=100000时，第2节基于id数组实现的并查集性能反超了基于第3节parent数组的，但是第5节经过层级rank优化后的UnionFind吊打前两个：
 * 11.2节 基于id数组的UnionFind实现：4.0056799s
 * 11.3节 基于parent数组的UnionFind实现：9.0071401s
 * 11.5节 基于parent数组的UnionFind实现，根据树高度rank堆union操作进行了优化：0.0120774s
 */