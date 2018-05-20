/***********************************************************
 * @Description : 数据类，后面课程的核心处理文件，算法基本都在这个里面实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 22:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SwingBasic.Section8To10MVCAndEventListener;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {
    private Circle[] circles;
    private AlgoFrame frame;
    /**
     * 动画效果是否开始，默认为true表示初始就有动画效果
     */
    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        // 圆的半径
        int R = 50;
        circles = new Circle[N];
        for (int i = 0; i < N; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
            // x和y方向的速度，在15——5之间
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            // 添加自定义的监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            // 在线程中调用自己的run方法
            new Thread(this::run).start();
        });
    }

    private void run() {
        while (true) {
            // 绘制
            frame.render(circles);
            // 暂停20ms
            AlgoVisualHelper.pause(20);
            // 标志位为True才进行位置更新
            if (isAnimated) {
                // 更新圆的位置
                for (Circle circle : circles) {
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
                }
            }
        }
    }

    /**
     * 封装自己的键盘响应事件，也可以implements KeyListener,但是必须实现KeyTyped、KeyPressed、KeyReleased事件
     */
    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                // 空格键取反标志位
                isAnimated = !isAnimated;
            }
        }
    }

    /**
     * 封装自己的键盘响应事件，也可以implements MouseListener,但是必须实现KeyTyped、KeyPressed、KeyReleased事件
     */
    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            // 利用位移差把MenuBar的高度影响消除掉
            event.translatePoint(0, -(frame.getBounds().height - frame.getCanvasHeight()));
            for (Circle circle : circles) {
                if (circle.contain(event.getPoint())) {
                    // 如果鼠标点击的点在某个圆内
                    circle.isFilled = !circle.isFilled;
                }
            }
        }
    }
}
