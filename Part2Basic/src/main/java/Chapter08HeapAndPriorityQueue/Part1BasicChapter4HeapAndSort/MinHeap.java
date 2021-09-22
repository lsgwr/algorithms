/***********************************************************
 * @Description : 最小堆
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/11 00:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Part1BasicChapter4HeapAndSort;

public class MinHeap<Item extends Comparable> {
    /**
     * 数据内容
     */
    protected Item[] data;
    /**
     * 元素数量
     */
    protected int count;
    /**
     * 数组容量
     */
    protected int capacity;

    MinHeap(int capacity) {
        // 不能直接生成泛型数组，所以要下面这么做
        data = (Item[]) new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    /**
     * 直接输入完整的数组进行最小堆化(Heapify)
     * 核心在于从(n/2结果取整)下标位置的元素开始到下标为0的元素依次执行shiftDown,好好理解4-5老师前8分钟的图示
     */
    MinHeap(Item arr[]) {
        int n = arr.length;
        // 声明空间，还没赋值
        data = (Item[]) new Comparable[n + 1];
        capacity = n;
        // 挨个赋值
        for (int i = 0; i < n; i++) {
            data[i + 1] = arr[i];
        }
        // 堆中元素个数为n了
        count = n;
        // 核心 : 从floor(n/2)下标位置的元素开始到下标为0的元素一次执行shiftDown,好好理解4-5老师前8分钟的图示
        // 执行完，就根据数组生成一个最小堆了
        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
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
    private void shiftUp(int k) {
        // k > 1是因为到根节点时就没父节点了，不需要再比较了
        while (k > 1 && data[k / 2].compareTo(data[k]) > 0) {
            // 如果当前元素的父元素(在数组中的下表为整除2)比当前元素还大，
            // 则不符合最小堆的定义，那么就交换一下
            swap(k / 2, k);
            k /= 2;
        }
    }

    /**
     * 把移上来的最后一个元素下移到合适位置
     */
    private void shiftDown(int k) {
        // 当当前k节点有子节点的时候(有左孩子不一定有右孩子，但有右孩子一定有左孩子。因为生成堆得时候是从左向右地)
        // 等于号别漏
        while (2 * k <= count) {
            // 获取子孩子下标
            int j = 2 * k;
            // 存在右孩子并且右孩子小于左孩子，那么右孩子有父节点交换(左右两个子节点的较小值)
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) < 0) {
                // 选择右节点作为下面与k处节点进行交换
                j++;
            }
            // 父节点比两个子节点的较小值还小，那么不需要交换
            if (data[k].compareTo(data[j]) <= 0) {
                break;
            }
            // 父节点大于孩子节点的较小值，那么就和较小值的子节点交换
            swap(k, j);
            // 把换后的子节点作为父节点，接着往下走
            k = j;
        }
    }


    /**
     * 获取最小堆的大小
     */
    int size() {
        return count;
    }

    /**
     * 判断最小堆是否为空
     */
    boolean isEmpty() {
        return count == 0;
    }

    /**
     * 插入元素
     */
    void insert(Item item) {
        // 防止后面的++count越界
        assert (count + 1 <= capacity);
        // 插入新元素，元素数加1,之所以用++count而不用count++是因为数组下标从1开始
        data[++count] = item;
        // 把新加入的元素向上浮动到合适位置
        shiftUp(count);
    }

    /**
     * 弹出最小值(根节点的对象)
     */
    Item popMin() {
        // 保证堆不为空
        assert (count > 0);
        // 最小元素为第一个元素
        Item min = data[1];
        // 移出最小元素后，需要把最下面的元素移到上面去
        swap(1, count);
        // 少了最后一个元素
        count--;
        // 将换上去(到最上面了,根元素，下标为1)的最后一个元素下移
        shiftDown(1);
        return min;
    }

    // 获取堆中的最小元素(即堆顶元素)
    Item getMin() {
        assert (count > 0);
        return data[1];
    }

    /**
     * 测试 MinHeap
     */
    public static void main(String[] args) {

        MinHeap<Integer> minHeap = new MinHeap<Integer>(100);
        // 堆中元素个数
        int N = 100;
        // 堆中元素取值范围[0, M)
        int M = 100;
        for (int i = 0; i < N; i++) {
            minHeap.insert(new Integer((int) (Math.random() * M)));
        }

        Integer[] arr = new Integer[N];
        // 将minheap中的数据逐渐使用extractMin取出来
        // 取出来的顺序应该是按照从小到大的顺序取出来的
        for (int i = 0; i < N; i++) {
            arr[i] = minHeap.popMin();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从小到大排列的
        for (int i = 1; i < N; i++) {
            assert arr[i - 1] <= arr[i];
        }
    }
}
