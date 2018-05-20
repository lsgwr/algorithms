/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 20:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SwingBasic.Section2AlgoVisualClass;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Welcome", 500, 500);
        });
    }
}
