/***********************************************************
 * @Description : 状态压缩工具类
 * 0代表false，1代表true
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/21 17:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09HamiltonLoop.Section8StateCompression;

public class StateCompression {

    /**
     * 判断数字num的第i位是否为1
     *
     * @param num 要检查的数字
     * @param i   要检查的位置
     * @return 是1返回true，不是1返回false
     */
    public static boolean isTrue(int num, int i) {
        return (num & (1 << i)) != 0;
    }

    /**
     * 设置num的第i为为1
     *
     * @param num 要修改的数字
     * @param i   要修改的位置
     * @return 修改后的数字
     */
    public static int setTrue(int num, int i) {
        return num + (1 << i);
    }

    /**
     * 设置num的第i为为0
     *
     * @param num 要修改的数字
     * @param i   要修改的位置
     * @return 修改后的数字
     */
    public static int setFalse(int num, int i) {
        return num - (1 << i);
    }
}
