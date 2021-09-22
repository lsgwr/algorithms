/***********************************************************
 * @Description : 测试并查集的工具类，用于测试不同UF实现的性能
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/2 22:51
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11UnionFind.Section4TestUF;

import Chapter11UnionFind.UF;

import java.util.Random;

public class TestUF {
    /**
     * 测试各种并查集的实现
     *
     * @param uf 并查集接口实例， 可以传入我们自己的各种UF实现
     * @param m  执行多少次union和isConnected操作
     * @return 执行时间，单位s
     */
    public static double test(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        // m次union
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }
        // m次isConnected()
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
