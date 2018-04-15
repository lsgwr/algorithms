/***********************************************************
 * @Description : 归并排序的算法优化(判断arr[mid]是否大于arr[mid+1]是地话再接着往下进行merge)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/14 14:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter3SortingSenior.Section6QuickSortDealWithNearlyOrderedArray;

import java.lang.reflect.Method;

public class SortTestHelper {

    private SortTestHelper() {
    }

    /**
     * 生成有n个元素的随机数组(仅限于整型数组)，每个元素的随机范围为[rangeL, rangeR)
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert (rangeL < rangeR);

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        }
        return arr;
    }

    /**
     * 打印arr数组的全部内容
     */
    public static void printArray(Object[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 测试sortClassName所对应的排序算法arr数组所得到结果的正确性和算法运行时间
     *
     * @param sortClassName 排序类的类名称，可使用反射获取这个类的实例
     * @param arr           待排序数组
     */
    public static void testSort(String sortClassName, Comparable[] arr) {
        try {
            // 通过Java的反射机制，通过排序的类名，运行排序函数
            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得排序方法.name表示排序类中的排序方法名。第二个参数表示sort函数的入参必须是可比较地
            Method sortMethod = sortClass.getMethod("sort", new Class[]{Comparable[].class});
            // 排序参数只有一个。是可比较数组arr
            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();
            // 调用排序函数
            sortMethod.invoke(null, params);
            long endTime = System.currentTimeMillis();

            assert isSorted(arr);

            System.out.println(sortClass.getSimpleName() + " : " + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isSorted(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 把指定数组的两个指定下标的元素互换
     *
     * @param arr 指定数组
     * @param i   指定下标1
     * @param j   指定下标2
     */
    public static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
