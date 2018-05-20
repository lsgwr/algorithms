/***********************************************************
 * @Description : 专门用来描绘算法的窗口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 20:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SwingBasic.Section11Template;


import Chapter2SwingBasic.Section8To10MVCAndEventListener.AlgoVisualHelper;

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

    /**
     * TODO:设置自己的数据(数据层Model--M)
     */
    private Object data;

    public void render(Object data) {
        this.data = data;
        // JFrame类里的函数
        repaint();
    }


    /**
     * 要执行的各种绘图操作
     */
    public void draw(Graphics2D g2d) {
        // TODO: 使用上面的data来进行各种绘图操作(视图层View--V)
    }

    private class AlgoCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 个性化地进行绘制，Graphics2D的功能更强大，所以先转换到2D，再进行绘图操作
            Graphics2D g2d = (Graphics2D) g;
            draw(g2d);
        }

        @Override
        public Dimension getPreferredSize() {
            // 重写这个方法可以自动确定画布的宽度和高度，不需要早创建画框的时候显示指定了
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
