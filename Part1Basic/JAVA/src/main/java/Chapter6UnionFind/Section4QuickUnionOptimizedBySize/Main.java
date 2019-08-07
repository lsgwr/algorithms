/***********************************************************
 * @Description : 测试并查集的执行类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/29 22:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter6UnionFind.Section4QuickUnionOptimizedBySize;

public class Main {
    public static void main(String[] args) {

        // 使用10000的数据规模
        int n = 1000000;

        // 改变了union时的指向，带来了成千上万倍的速度提升
        UnionFindTestHelper.testUF(n);
    }
}
