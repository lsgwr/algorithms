/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/16 00:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter4Heap.Section2to4;

import java.util.Comparator;
import java.util.PriorityQueue;

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
    private void shiftUp(int k) {
        // k > 1是因为到根节点时就没父节点了，不需要再比较了
        while (k > 1 && data[k / 2].compareTo(data[k]) < 0) {
            // 如果当前元素的父元素(在数组中的下表为整除2)比当前元素还小，
            // 则不符合最大堆的定义，那么就交换一下
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
            // 存在右孩子并且右孩子大于左孩子，那么右孩子有父节点交换
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
                // 选择右节点作为下面与k处节点进行交换
                j++;
            }
            // 父节点比两个子节点的较大值还大，那么不需要交换
            if (data[k].compareTo(data[j]) >= 0) {
                break;
            }
            // 父节点小于孩子节点的较大值，那么就和较大值的子节点交换
            swap(k, j);
            // 把换后的子节点作为父节点，接着往下走
            k = j;
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

    /**
     * 弹出最大值(根节点的对象)
     */
    public Item popMax() {
        // 保证堆不为空
        assert (count > 0);
        // 最大元素为第一个元素
        Item max = data[1];
        // 移出最大元素后，需要把最下面的元素移到上面去
        swap(1, count);
        // 少了最后一个元素
        count--;
        // 将换上去(到最上面了,根元素，下标为1)的最后一个元素下移
        shiftDown(1);
        return max;
    }

    public static void main(String[] args) {
        // 自己实现的优先队列
        MaxHeap<Integer> maxHeap = new MaxHeap<>(100);
        for (int i = 0; i < 31; i++) {
            maxHeap.insert((int) (Math.random() * i));
        }
        System.out.println(maxHeap.size());
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.popMax() + " ");
        }
        System.out.println();

        // Java自带的有限队列PriorityQueue
        int[] arr = new int[]{2, 5, 3, 9, 6, 4, 7};


        // 1.排序(升序， 默认)
        System.out.println("PriorityQueue默认的升序：");
        PriorityQueue<Integer> pqAsc = new PriorityQueue<>();
        for (int i : arr) {
            pqAsc.offer(i);
        }
        while (!pqAsc.isEmpty()) {
            System.out.print(pqAsc.poll() + " ");
        }
        System.out.println();

        // 2.排序(降序)
        System.out.println("PriorityQueue自定义的降序");
        PriorityQueue<Integer> pqDesc = new PriorityQueue<>(arr.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                return num2.compareTo(num1);
            }
        });
        for (int i : arr) {
            pqDesc.offer(i);
        }
        while (!pqDesc.isEmpty()) {
            System.out.print(pqDesc.poll() + " ");
        }
        System.out.println();

        // 3.取出最大的3个元素
        System.out.println("取出最大的3个元素(默认):");
        PriorityQueue<Integer> pqMax = new PriorityQueue<>();

        for (int i : arr) {
            if (pqMax.size() < 3) {
                pqMax.offer(i);
            } else if (pqMax.peek() < i) {
                // 新元素大于堆顶元素，弹出堆顶元素，并把新的最大值加进去
                pqMax.poll();
                pqMax.offer(i);
            }
        }
        while (!pqMax.isEmpty()) {
            System.out.print(pqMax.poll() + " ");
        }
        System.out.println();


        System.out.println("取出最小的3个元素(自定义):");
        PriorityQueue<Integer> pqMin = new PriorityQueue<>(arr.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                return num2.compareTo(num1);
            }
        });

        for (int i : arr) {
            if (pqMin.size() < 3) {
                pqMin.offer(i);
            } else if (pqMin.peek() > i) {
                // 新元素大于堆顶元素，弹出堆顶元素，并把新的最大值加进去
                pqMin.poll();
                pqMin.offer(i);
            }
        }
        while (!pqMin.isEmpty()) {
            System.out.print(pqMin.poll() + " ");
        }
        System.out.println();
    }
}
