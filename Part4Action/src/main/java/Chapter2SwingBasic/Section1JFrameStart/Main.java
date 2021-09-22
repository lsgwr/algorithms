/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 19:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SwingBasic.Section1JFrameStart;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Java事件线程触发，可以防止多个GUI逻辑互相影响
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Welcome");
            frame.setSize(500, 500);
            // 不允许用户改变窗口大小
            frame.setResizable(false);
            // 点击x号关闭窗口
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
