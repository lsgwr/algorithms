/***********************************************************
 * @Description : 测试第2节、第3节、第5节三种不同实现的并查集
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/2 23:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11UnionFind.Section6PathCompression;

import Chapter11UnionFind.Section4TestUF.TestUF;
import Chapter11UnionFind.UF;

public class Main {

    public static void main(String[] args) {
        // 并查集一共的节点数
        int size = 1000000;
        // 要进行union操作次数
        int m = 1000000;
        // 11.2和11.3的效率太低，执行时间太长，就不测了
        UF uf3 = new Chapter11UnionFind.Section5OptimizeByRank.UnionFind(size);
        System.out.println("11.5节 基于parent数组的UnionFind实现，根据树高度rank对union操作进行了优化：" + TestUF.test(uf3, m) + "s");

        UF uf4 = new Chapter11UnionFind.Section6PathCompression.UnionFind(size);
        System.out.println("11.6节 路径压缩，不断在find中把底部节点往上移动，：" + TestUF.test(uf4, m) + "s");
    }
}
/**
 * size=1000000，m=1000000时
 * 11.5节 基于parent数组的UnionFind实现，根据树高度rank对union操作进行了优化：0.2143887s
 * 11.6节 路径压缩，不断在find中把底部节点往上移动，：0.1687864s
 *
 * size=10000000，m=10000000时，路径压缩算法的优势就明显了
 * 11.5节 基于parent数组的UnionFind实现，根据树高度rank对union操作进行了优化：4.332558s
 * 11.6节 路径压缩，不断在find中把底部节点往上移动，：3.4321862s
 */