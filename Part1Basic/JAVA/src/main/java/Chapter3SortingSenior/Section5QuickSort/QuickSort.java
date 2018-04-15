/***********************************************************
 * @Description : 快速排序的最基本实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/15 18:43
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter3SortingSenior.Section5QuickSort;

public class QuickSort {
    /**
     * 不准实例化
     */
    private QuickSort() {
    }


    /**
     * 返回合适的且分点，最终结果如下：返回p，使得arr[left...p-1] < arr[p] ; arr[p+1...right] > arr[p]
     */
    private static int partition(Comparable[] arr, int left, int right) {
        // 选择后面所有元素都要与之比较的元素，一般直接选第一个就好
        Comparable ref = arr[left];
        //开始考察元素： arr[left...j] < v ; arr[j+1...i] > v
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            // arr[i] > ref的时候不需要挪动，因为已经在最右侧了
            if (arr[i].compareTo(ref) < 0) {
                // 小于参考元素的都忘左边挪
                SortTestHelper.swap(arr, j + 1, i);
                j++;
            }
        }
        // 最后把参考元素置换到中间去
        SortTestHelper.swap(arr, left, j);
        // 最后j的位置就是切分点应该在的位置
        return j;
    }

    /**
     * 对arr[left...right]内的元素进行快速排序，需要用到递归
     */
    public static void sort(Comparable[] arr, int left, int right) {
        // 左右指针相遇的时候，就直接退出
        if (left >= right) {
            return;
        }
        // 选择切分点，执行__partition使得arr[left...p-1] < arr[p] ; arr[p+1...right] > arr[p]
        int partition = partition(arr, left, right);
        // 递归进行左右两侧排序
        sort(arr, left, partition - 1);
        sort(arr, partition + 1, right);
    }

    /**
     * 从C++转换过来数组的长度一半都是不带地
     *
     * @param arr 待排序数据
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    public static void main(String[] args) {
        // 测试自定义的算法辅助函数
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, N);
        // 测试数组是否有序
        // sort函数一定不要穿数组长度，要不老会报错
        SortTestHelper.testSort("Chapter3SortingSenior.Section5QuickSort.QuickSort", arr);
    }
}
