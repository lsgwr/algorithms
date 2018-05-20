/***********************************************************
 * @Description : 算法可视化的工具类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 21:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SwingBasic.Section11Template;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class AlgoVisualHelper {
    private AlgoVisualHelper() {
    }

    public static final Color Red = new Color(0xF44336);
    public static final Color Pink = new Color(0xE91E63);
    public static final Color Purple = new Color(0x9C27B0);
    public static final Color DeepPurple = new Color(0x673AB7);
    public static final Color Indigo = new Color(0x3F51B5);
    public static final Color Blue = new Color(0x2196F3);
    public static final Color LightBlue = new Color(0x03A9F4);
    public static final Color Cyan = new Color(0x00BCD4);
    public static final Color Teal = new Color(0x009688);
    public static final Color Green = new Color(0x4CAF50);
    public static final Color LightGreen = new Color(0x8BC34A);
    public static final Color Lime = new Color(0xCDDC39);
    public static final Color Yellow = new Color(0xFFEB3B);
    public static final Color Amber = new Color(0xFFC107);
    public static final Color Orange = new Color(0xFF9800);
    public static final Color DeepOrange = new Color(0xFF5722);
    public static final Color Brown = new Color(0x795548);
    public static final Color Grey = new Color(0x9E9E9E);
    public static final Color BlueGrey = new Color(0x607D8B);
    public static final Color Black = new Color(0x000000);
    public static final Color White = new Color(0xFFFFFF);

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
     * 打开抗锯齿,使得边缘更平滑
     */
    public static void beSmooth(Graphics2D g2d) {
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);
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

    /**
     * 绘制空心矩形
     *
     * @param g2d    绘图对象
     * @param x      左上角点横坐标
     * @param y      左上角点纵坐标
     * @param weight 矩形宽度
     * @param height 矩形高度
     */
    public static void strokeRectangle(Graphics2D g2d, int x, int y, int weight, int height) {

        Rectangle2D rectangle = new Rectangle2D.Double(x, y, weight, height);
        g2d.draw(rectangle);
    }

    /**
     * 绘制实心矩形
     *
     * @param g2d    绘图对象
     * @param x      左上角点横坐标
     * @param y      左上角点纵坐标
     * @param weight 矩形宽度
     * @param height 矩形高度
     */
    public static void fillRectangle(Graphics2D g2d, int x, int y, int weight, int height) {

        Rectangle2D rectangle = new Rectangle2D.Double(x, y, weight, height);
        g2d.fill(rectangle);
    }

    /**
     * 暂停t毫秒
     *
     * @param t 毫秒
     */
    public static void pause(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("Error in Sleeping");
            e.printStackTrace();
        }
    }

    /**
     * 将一幅图加载到制定的位置
     *
     * @param g2d      绘图对象
     * @param x        图片绘制的左上角点横坐标
     * @param y        图片绘制的左上角点纵坐标
     * @param imageURL 图片地址
     */
    public static void putImage(Graphics2D g2d, int x, int y, String imageURL) {

        ImageIcon icon = new ImageIcon(imageURL);
        Image image = icon.getImage();

        g2d.drawImage(image, x, y, null);
    }

    /**
     * 把一段文字绘制到制定的点为中心的地方
     *
     * @param g2d     绘图对象
     * @param text    要绘制的文本
     * @param centerx 要绘制的位置的中心点横坐标
     * @param centery 要绘制的位置的中心点纵坐标
     */
    public static void drawText(Graphics2D g2d, String text, int centerx, int centery) {

        if (text == null) {
            throw new IllegalArgumentException("Text is null in drawText function!");
        }

        FontMetrics metrics = g2d.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getDescent();
        g2d.drawString(text, centerx - w / 2, centery + h);
    }
}
