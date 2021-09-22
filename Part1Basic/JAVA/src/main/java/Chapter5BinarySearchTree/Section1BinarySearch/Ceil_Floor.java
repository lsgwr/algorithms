/***********************************************************
 * @Description : 在查找过程中，floor函数用于返回小于target的最大值
 *                            ceil函数用于返回大于target的最小值
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/23 00:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter5BinarySearchTree.Section1BinarySearch;

public class Ceil_Floor {
    /**
     * 找不到target地话，返回小于target的最大值
     * 二分查找法, 在有序数组arr中, 查找target
     * 如果找到target, 返回第一个target相应的索引index
     * 如果没有找到target, 返回比target小的最大值相应的索引, 如果这个最大值有多个, 返回最大索引
     * 如果这个target比整个数组的最小元素值还要小, 则不存在这个target的floor值, 返回-1
     */
    public static int floor(Comparable[] arr, Comparable target) {
        int n = arr.length;
        assert (n >= 0);
        // 寻找比target小的最大索引
        int left = -1;
        int right = n - 1;
        // 寻找比target小的最大值的索引
        while (left < right) {
            // 向上取整防止死循环
            int middle = left + (right - left + 1) / 2;
            if (arr[middle].compareTo(target) >= 0) {
                right = middle - 1;
            } else {// arr[middle] < target
                left = middle;
            }
        }
        // 确认最后左右指针相遇了
        assert (left == right);

        // 第一种情况，该索引+1就是target，说明找到target了，直接返回该target的索引
        if ((left + 1 < n) && arr[left + 1] == target) {
            return left + 1;
        }

        // 第二种情况，说明没找到target，当前left索引返回即可
        return left;
    }

    /**
     * 找不到target地话，返回大于target的最小值
     * 二分查找法, 在有序数组arr中, 查找target
     * 如果找到target, 返回最后一个target相应的索引index
     * 如果没有找到target, 返回比target大的最小值相应的索引, 如果这个最小值有多个, 返回最小的索引
     * 如果这个target比整个数组的最大元素值还要大, 则不存在这个target的ceil值, 返回整个数组元素个数n
     */
    public static int ceil(Comparable[] arr, Comparable target) {
        int n = arr.length;
        assert (n >= 0);
        // 寻找比target大的最小值对应的索引
        int left = 0;
        int right = n;
        // 寻找比target小的最大值的索引
        while (left < right) {
            // 向下取整防止死循环
            int middle = left + (right - left) / 2;
            if (arr[middle].compareTo(target) <= 0) {
                left = middle + 1;
            } else { // arr[middle] < target
                right = middle;
            }
        }
        // 确认最后左右指针相遇了
        assert (left == right);

        // 第一种情况，该索引-1就是target，说明找到target了，直接返回该target的索引
        if ((right - 1 >= 0) && arr[right - 1] == target) {
            return right - 1;
        }

        // 第二种情况，说明没找到target，当前left索引返回即可
        return right;
    }

    /**
     * 测试我们用二分查找法实现的floor和ceil两个函数
     * 请仔细观察在我们的测试用例中，有若干的重复元素，对于这些重复元素，floor和ceil计算结果的区别：）
     */
    public static void main(String[] args) {
        Integer[] a = {1, 1, 1, 2, 2, 2, 2, 2, 4, 4, 5, 5, 5, 6, 6, 6};
        int n = a.length;
        for (int i = 0; i <= 8; i++) {

            int floorIndex = floor(a, i);
            System.out.print("the floor index of " + i + " is " + floorIndex + ".");
            if (floorIndex >= 0 && floorIndex < n) {
                System.out.print("The value is " + a[floorIndex] + ".");
            }
            System.out.println();
            int ceilIndex = ceil(a, i);
            System.out.print("the ceil index of " + i + " is " + ceilIndex + ".");
            if (ceilIndex >= 0 && ceilIndex < n) {
                System.out.print("The value is " + a[ceilIndex] + ".");
            }
            System.out.println();
            System.out.println();
        }

    }

}
