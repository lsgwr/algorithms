/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/14 14:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SortingBasic.Section3GenerateTestCases;

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
}
