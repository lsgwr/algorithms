/***********************************************************
 * @Description : 对数组进行递归求和
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/29 10:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05Recursion.Section3RecursionBasic;

public class ArrSum {
    private int res = 0;

    public int sum(int[] arr) {
        return sum(arr, 0);
    }

    /**
     * 计算从arr[i]到arr[n-1]的和
     *
     * @param arr 数组
     * @param i   当前遍历到的索引
     * @return arr[i]到arr[n-1]的和
     */
    private int sum(int[] arr, int i) {
        if (i == arr.length) {
            // 遍历完数组，返回0
            return 0;
        }
        return arr[i] + sum(arr, i + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(new ArrSum().sum(arr));
    }
}
