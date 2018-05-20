/***********************************************************
 * @Description : 算法可视化的工具类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 21:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SwingBasic.Section2AlgoVisualClass;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class AlgoVisualHelper {
    private AlgoVisualHelper() {
    }

    /**
     * 设置绘制线条的宽度
     *
     * @param g2d   核心绘图对象
     * @param width 要设置的线条宽度
     */
    public static void setStrokeWidth(Graphics2D g2d, int width) {
        // CAP_ROUND表示端点平滑；JOIN_ROUND表示转折点(拐点)平滑
        g2d.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    /**
     * 设置画笔颜色
     *
     * @param g2d   绘图对象
     * @param color 颜色对象
     */
    public static void setColor(Graphics2D g2d, Color color) {
        g2d.setColor(color);
    }

    /**
     * 画空心圆，根据圆心和半径进行绘图
     *
     * @param g2d 绘图对象
     * @param x   圆心x坐标
     * @param y   圆心y坐标
     * @param r   半径
     */
    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(circle);
    }

    /**
     * 画实心圆，根据圆心和半径进行绘图
     *
     * @param g2d 绘图对象
     * @param x   圆心x坐标
     * @param y   圆心y坐标
     * @param r   半径
     */
    public static void filleCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(circle);
    }
}
