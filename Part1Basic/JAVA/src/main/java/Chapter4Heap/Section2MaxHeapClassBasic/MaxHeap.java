/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/16 00:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter4Heap.Section2MaxHeapClassBasic;

/**
 * 在堆的有关操作中，需要比较堆中元素的大小，所以Item需要extends Comparable
 */
public class MaxHeap<Item extends Comparable> {
    /**
     * 最大堆中的数据
     */
    protected Item[] data;
    /**
     * 最大堆中的元素数量
     */
    protected int count;
    /**
     * 数组容量
     */
    protected int capacity;

    public MaxHeap(int capacity) {
        // 不能直接声明泛型数组，只能先声明再强制转换
        this.data = (Item[]) new Comparable[capacity];
        count = 0;
        this.capacity = capacity;
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
    public boolean isEmpty() {
        return count == 0;
    }


    /**
     * 交换堆中索引为i和j的两个元素
     */
    private void swap(int i, int j) {
        Item t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    /**
     * 上浮的私有函数
     */
    public void shiftUp(int k) {
        // k > 1是因为到根节点时就没父节点了，不需要再比较了
        while (k > 1 && data[k / 2].compareTo(data[k]) < 0) {
            // 如果当前元素的父元素(在数组中的下表为整除2)比当前元素还小，
            // 则不符合最大堆的定义，那么就交换一下
            swap(k / 2, k);
            k /= 2;
        }
    }

    /**
     * 插入元素
     */
    public void insert(Item item) {
        // 防止后面的++count越界
        assert (count + 1 <= capacity);
        // 插入新元素，元素数加1,之所以用++count而不用count++是因为数组下标从1开始
        data[++count] = item;
        // 把新加入的元素向上浮动到合适位置
        shiftUp(count);
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(100);
        System.out.println(maxHeap.size());
    }
}
