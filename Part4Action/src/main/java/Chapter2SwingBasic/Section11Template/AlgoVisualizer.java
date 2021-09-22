/***********************************************************
 * @Description : 数据类，后面课程的核心处理文件，算法基本都在这个里面实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 22:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SwingBasic.Section11Template;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {
    /**
     * 数据层Model--M
     */
    private Object data;
    /**
     * 视图层View--V
     */
    private AlgoFrame frame;
    /**
     * 动画效果是否开始，默认为true表示初始就有动画效果
     */
    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        // TODO:初始化数据data

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            // TODO: 自己决定好是否要添加自定义的监听器(下面两行都是)
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            // 在线程中调用自己的run方法
            new Thread(this::run).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run() {
        // TODO: 编写自己的动画逻辑
    }


    // TODO: 自己决定是否要实现键盘鼠标等交互事件监听类(下面两个类，override Adapter中的方法即可)

    /**
     * 封装自己的键盘响应事件
     */
    private class AlgoKeyListener extends KeyAdapter {

    }

    /**
     * 封装自己的键盘响应事件
     */
    private class AlgoMouseListener extends MouseAdapter {
    }
}
