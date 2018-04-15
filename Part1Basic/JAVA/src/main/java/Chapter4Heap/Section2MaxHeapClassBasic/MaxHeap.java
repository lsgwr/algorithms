/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/16 00:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter4Heap.Section2MaxHeapClassBasic;

public class MaxHeap<Item> {
    /**
     * 最大堆中的数据
     */
    private Item[] data;
    /**
     * 最大堆中的元素数量
     */
    private int count;

    public MaxHeap(int capacity) {
        // 不能直接声明泛型数组，只能先声明再强制转换
        this.data = (Item[]) new Object[capacity];
        count = 0;
    }

    /**
     * 获取堆中元素个数
     *
     * @return 堆中元素个数
     */
    public int size() {
        return count;
    }

    /**
     * 堆是否为空
     */
    boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(100);
        System.out.println(maxHeap.size());
    }
}
