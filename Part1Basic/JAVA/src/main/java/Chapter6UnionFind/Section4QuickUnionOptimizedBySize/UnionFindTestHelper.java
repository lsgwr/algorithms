/***********************************************************
 * @Description : 测试并查集的辅助类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/29 22:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter6UnionFind.Section4QuickUnionOptimizedBySize;



public class UnionFindTestHelper {
    public static void testUF(int n) {

        UnionFind uf = new UnionFind(n);

        long startTime = System.currentTimeMillis();

        // 进行n次操作, 每次随机选择两个元素进行合并操作
        for (int i = 0; i < n; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            uf.unionElements(a, b);
        }
        // 再进行n次操作, 每次随机选择两个元素, 查询他们是否同属一个集合
        for (int i = 0; i < n; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            uf.isConnected(a, b);
        }
        long endTime = System.currentTimeMillis();

        // 打印输出对这2n个操作的耗时
        System.out.println("UF, " + 2 * n + " ops, " + (endTime - startTime) + "ms");
    }
}
