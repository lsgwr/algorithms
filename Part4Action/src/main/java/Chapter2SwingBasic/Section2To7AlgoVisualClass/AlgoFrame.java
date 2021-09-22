/***********************************************************
 * @Description : 专门用来描绘算法的窗口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 20:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SwingBasic.Section2To7AlgoVisualClass;


import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {
    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        AlgoCanvas canvas = new AlgoCanvas();
        // 设置Frame的内容面板
        setContentPane(canvas);
        // 根据加载进窗口的内容自动调整大小
        pack();
        // 不允许用户改变窗口大小
        this.setResizable(false);
        // 点击x号关闭窗口
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public AlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private Circle[] circles;

    public void render(Circle[] circles) {
        this.circles = circles;
        // JFrame类里的函数
        repaint();
    }

    /**
     * 绘制一个外边框是蓝色，用红色填充的实心圆
     */
    public void test1(Graphics2D g2d) {
        // 打开抗锯齿,使得边缘更平滑
        AlgoVisualHelper.beSmooth(g2d);
        // 设置线条宽度
        AlgoVisualHelper.setStrokeWidth(g2d, 5);
        // 设置线条颜色
        AlgoVisualHelper.setColor(g2d, Color.RED);
        // 绘制实心圆
        AlgoVisualHelper.filleCircle(g2d, canvasWidth / 2, canvasHeight / 2, 200);
        // 重新设置线条颜色
        AlgoVisualHelper.setColor(g2d, Color.BLUE);
        // 绘制空心圆
        AlgoVisualHelper.strokeCircle(g2d, canvasWidth / 2, canvasHeight / 2, 200);
    }

    /**
     * 绘制一个外边框是蓝色，用红色填充的实心圆
     */
    public void test2(Graphics2D g2d) {
        AlgoVisualHelper.beSmooth(g2d);
        AlgoVisualHelper.setStrokeWidth(g2d, 1);
        AlgoVisualHelper.setColor(g2d, Color.RED);
        for (Circle circle : circles) {
            AlgoVisualHelper.strokeCircle(g2d, circle.x, circle.y, circle.getR());
        }
    }

    private class AlgoCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 个性化地进行绘制，Graphics2D的功能更强大，所以先转换到2D，再进行绘图操作
            Graphics2D g2d = (Graphics2D) g;
            // test1(g2d); //绘制一个外边框是蓝色，用红色填充的实心圆
            test2(g2d);  // 动画，把各个圆依次画出来
        }

        @Override
        public Dimension getPreferredSize() {
            // 重写这个方法可以自动确定画布的宽度和高度，不需要早创建画框的时候显示指定了
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
