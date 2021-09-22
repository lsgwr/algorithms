/***********************************************************
 * @Description : 圆的基础类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 21:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2SwingBasic.Section2To7AlgoVisualClass;

public class Circle {

    public int x;
    public int y;
    private int r;

    public int vx;
    public int vy;

    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public int getR() {
        return r;
    }

    /**
     * 移动函数
     *
     * @param minx x最大值
     * @param miny x最小值
     * @param maxx y最大值
     * @param maxy y最小值
     */
    public void move(int minx, int miny, int maxx, int maxy) {
        x += vx;
        y += vy;

        checkCollision(minx, miny, maxx, maxy);
    }

    /**
     * 碰撞检测
     *
     * @param minx x最大值
     * @param miny x最小值
     * @param maxx y最大值
     * @param maxy y最小值
     */
    private void checkCollision(int minx, int miny, int maxx, int maxy) {

        if (x - r < minx) {
            x = r;
            vx = -vx;
        }
        if (x + r >= maxx) {
            x = maxx - r;
            vx = -vx;
        }
        if (y - r < miny) {
            y = r;
            vy = -vy;
        }
        if (y + r >= maxy) {
            y = maxy - r;
            vy = -vy;
        }
    }

}
