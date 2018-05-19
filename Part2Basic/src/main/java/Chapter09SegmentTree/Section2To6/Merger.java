/***********************************************************
 * @Description : 线段树种左右子节点的合并
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/19 12:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09SegmentTree.Section2To6;

public interface Merger<Element> {
    /**
     * 这里根据业务特点决定每个节点的值合并方式是sum、max、min、avg等.
     * 即综合两段子树来得到父节点的数据
     */
    Element merge(Element a, Element b);
}
