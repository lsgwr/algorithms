import java.lang.reflect.Array;
import java.util.*;

class Solution {
    /**
     * 当前点求上右下左四个点时用到的矢量差,dirs 是directions的意思
     */
    private int[][] dirs = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    /**
     * 传进来的grid有多少行(Row)多少列(Col)
     */
    private int R;
    private int C;

    /**
     * 岛屿网格的情况
     */
    private int[][] grid;

    /**
     * 判断grid中所有的点是否被访问，因为此时(x, y)共同确定一个点，所以visited也是二维数组，数组的值表示联通分量的编号
     */
    private int[][] visited;

    /**
     * 联通分量的个数，dfs过程中可以认为是联通分量的编号
     */
    private int ccCount;

    /**
     * 判断第x行第y列的点(x, y)是否在grid所在的范围内
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    /**
     * Todo:首先找到连通分量的个数和具体的点，然后计算点数相同的联通分量各个点的坐标差是否相等，相等则视为一个岛屿
     */
    public int numDistinctIslands(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        R = grid.length;
        if (R == 0) {
            // 如果行数为0说明图为空，直接返回
            return 0;
        }
        C = grid[0].length;
        if (C == 0) {
            // 如果列数为0说明图为空，直接返回
            return 0;
        }
        this.grid = grid;
        // 构造出想要的图，图实际就是简化后的邻接表形式的图
        // 一共有多少个不同的联通分量(平移后重合的认为是一个联通分量)
        int result = 0;
        // visited的长度就是graph的长度，即grid内所有的节点数
        visited = new int[R][C];
        // 访问数组全部初始化为-1
        for (int[] col : visited) {
            Arrays.fill(col, -1);
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j] == -1 && grid[i][j] == 1) {
                    // grid[i][j] == 1表示(i, j)是陆地,而且还没被访问，然后才有必要继续往下遍历
                    dfs(i, j, ccCount);
                    // dfs遍历完一次，说明多个一个联通分量
                    ccCount++;
                }
            }
        }
        // Todo:根据visited数组获取联通分量内的点的具体情况，然后计算点数相同的联通分量各个点的坐标差是否相等，相等则视为一个岛屿
        Map<Integer, List<int[]>> connectedComponentsMap = getConnectedComponentsMap();
        // 记录那个联通分量时重复的，最后repeated里面为false的就是不同的岛屿
        boolean[] repeated = new boolean[ccCount];
        for (int i = 0; i < ccCount; i++) {
            for (int j = i + 1; j < ccCount; j++) {
                List<int[]> pointList1 = connectedComponentsMap.get(i);
                List<int[]> pointList2 = connectedComponentsMap.get(j);
                if (!repeated[j] && pointList1.size() == pointList2.size()) {
                    boolean isRepeated = true;
                    // 初始行差
                    int rDiffInit = pointList1.get(0)[0] - pointList2.get(0)[0];
                    // 初始列差
                    int cDiffInit = pointList1.get(0)[1] - pointList2.get(0)[1];
                    for (int k = 1; k < pointList1.size(); k++) {
                        int rDiff = pointList1.get(k)[0] - pointList2.get(k)[0];
                        int cDiff = pointList1.get(k)[1] - pointList2.get(k)[1];
                        if (rDiff != rDiffInit || cDiff != cDiffInit) {
                            // 一个对应坐标差不同，则两个联通分量不重复，接着比较下一个联通分量
                            isRepeated = false;
                            break;
                        }
                    }
                    if (isRepeated) {
                        repeated[j] = true;
                    }
                }
            }
        }
        for (boolean b : repeated) {
            if (!b) {
                result++;
            }
        }
        return result;
    }

    /**
     * 深度优先遍历
     *
     * @param x    当前遍历到的顶点下标行
     * @param y    当前遍历到的顶点下标列
     * @param ccid 当前连通分量的标记(同一个连通分量内的元素都在visited数组内用这个数值进行赋值标记)
     */
    private void dfs(int x, int y, int ccid) {
        visited[x][y] = ccid;
        // 遍历节点(x, y)所有的邻接点,判断是陆地的
        for (int[] dir : dirs) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            // 点(nextX, nextY)必须在grid区域内 + 没被访问过 + 是陆地(点(x, y)是陆地)
            if (inArea(nextX, nextY) && visited[nextX][nextY] == -1 && grid[nextX][nextY] == 1) {
                dfs(nextX, nextY, ccid);
            }
        }
    }

    /**
     * 获取连通分量的详细信息，Map的key是ccid，value是ccid对应连通分量内的所有元素
     */
    public Map<Integer, List<int[]>> getConnectedComponentsMap() {
        Map<Integer, List<int[]>> connectedComponentsMap = new TreeMap<>();
        for (int ccid = 0; ccid < ccCount; ccid++) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    // 只有当已访问数组中vertexIndex下标对应的值等于ccid才加到ccid对应的连通分量List中
                    if (ccid == visited[i][j]) {
                        // 给Map赋值
                        if (connectedComponentsMap.get(ccid) == null) {
                            // 以前没加过这个连通分量地话
                            List<int[]> ccList = new ArrayList<>();
                            // 把点的坐标加进去
                            ccList.add(new int[]{i, j});
                            // 把连通分量加进去
                            connectedComponentsMap.put(ccid, ccList);
                        } else {
                            // 以前加入过ccList了，那么这次直接往list里加元素即可
                            connectedComponentsMap.get(ccid).add(new int[]{i, j});
                        }
                    }
                }
            }
        }
        return connectedComponentsMap;
    }

    /**
     * int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
     */
    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        System.out.println(new Solution().numDistinctIslands(grid));
    }
}