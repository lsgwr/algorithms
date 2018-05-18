/***********************************************************
 * @Description : 线段树
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/18 23:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09SegmentTree.Section2Basic;

public class SegmentTree<Element> {
    private Element[] tree;
    private Element[] data;

    public SegmentTree(Element[] arr) {
        this.data = (Element[]) new Object[arr.length];
        // 数组复制
        System.arraycopy(arr, 0, data, 0, arr.length);
        tree = (Element[]) new Object[4 * arr.length];
    }

    public Element get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    public int getSize() {
        return data.length;
    }

    /**
     * 返回二叉树的数组表示中的一个索引所表示的元素的左孩子节点的索引
     */
    public int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 返回二叉树的数组表示中的一个索引所表示的元素的右孩子节点的索引
     */
    public int rightChild(int index) {
        return 2 * index + 2;
    }
}
