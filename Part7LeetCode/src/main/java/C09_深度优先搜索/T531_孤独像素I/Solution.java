/***********************************************************
 * @Description : 531. 孤独像素 I
 * https://leetcode-cn.com/problems/lonely-pixel-i/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/18 23:53
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T531_孤独像素I;

class Solution {
    /**
     * 行数
     */
    private int R;

    /**
     * 列数
     */
    private int C;

    /**
     * 图片对象
     */
    private char[][] picture;

    /**
     * 判断(r, c)处的像素是否黑孤独像素
     *
     * @param r 行号
     * @param c 列号
     * @return 是否是孤独像素
     */
    boolean isLonelyPixel(int r, int c) {
        // 检查行
        for (int i = 0; i < R; i++) {
            if (i == r){
                continue;
            }
            if (picture[i][c] == 'B'){
                return false;
            }
        }
        // 检查列
        for (int j = 0; j < C; j++) {
            if (j == c){
                continue;
            }
            if (picture[r][j] == 'B'){
                return false;
            }
        }
        return true;
    }

    public int findLonelyPixel(char[][] picture) {
        R = picture.length;
        C = picture[0].length;
        int result = 0;
        this.picture = picture;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (picture[r][c] == 'B' && isLonelyPixel(r, c)) {
                    result++;
                }
            }
        }
        return result;
    }
}
