/***********************************************************
 * @Description : 测试并查集的执行类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/29 22:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11UnionFind;

public class Main {
    public static void main(String[] args) {

        // 使用10000的数据规模
        int n = 1000000;

        // 虽然isConnected只需要O(1)的时间, 但由于union操作需要O(n)的时间
        // 总体测试过程的算法复杂度是O(n^2)的
        UnionFindTestHelper.testUF(n);
    }
}
