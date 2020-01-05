/***********************************************************
 * @Description : 只要实现了compareTo接口的都可以来排序，支持了更多类型
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/14 11:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SortingBasic.Section2SortUsingTemplate;

public class SelectionSort {
    // 算法类不允许产生任何实例，简单的单例模式，没有做同步

    private SelectionSort() {
    }

    /**
     * 对任何可比较的元素数组进行排序,默认是进行从小到大排序(升序)
     *
     * @param arr 指定的数组
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            // 寻找[i, n)区间里的最小值的索引
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // 把本轮的最小元素的位置和循环的起始位置进行置换
            swap(arr, i, minIndex);
        }
    }

    /**
     * 把指定数组的两个指定下标的元素互换
     *
     * @param arr 指定数组
     * @param i   指定下标1
     * @param j   指定下标2
     */
    private static void swap(Object[] arr, int i, int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        // 1.整型数组排序
        Integer[] a = {10, 9, 5, 6, 8, 7, 2, 1, 3, 4};
        sort(a);
        for (int i = 0; i < 10; ++i) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        // 2.对浮点数进行排序
        Float[] b = {2.2f, 1.1f, 4.4f, 3.3f};
        sort(b);
        for (int i = 0; i < 4; ++i) {
            System.out.print(b[i] + " ");
        }
        System.out.println();
        // 3.字符数组排序
        Character[] c = {'B', 'A', 'D', 'C'};
        sort(c);
        for (int i = 0; i < 4; ++i) {
            System.out.print(c[i] + " ");
        }
        System.out.println();
        // 4.字符串数组排序
        String[] d = {"dba", "abd", "bbc", "abc"};
        sort(d);
        for (int i = 0; i < 4; i++) {
            System.out.print(d[i] + " ");
        }
        System.out.println();

        // 5.自定义对象排序，按照分数排序
        Student[] students = new Student[4];
        students[0] = new Student("B", 78);
        students[1] = new Student("A", 86);
        students[2] = new Student("G", 34);
        students[3] = new Student("D", 78);
        sort(students);
        for (int i = 0; i < 4; i++) {
            System.out.println(students[i]);
        }
        System.out.println();
    }
}
/**
 * 1 2 3 4 5 6 7 8 9 10
 * 1.1 2.2 3.3 4.4
 * A B C D
 * abc abd bbc dba
 * Student(name=G, score=34)
 * Student(name=B, score=78)
 * Student(name=D, score=78)
 * Student(name=A, score=86)
 */