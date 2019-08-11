/***********************************************************
 * @Description : 测试二进制矩阵中的最短路径
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/8/11 11:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07AISearchAndBFS.Section1EightConnectedAndBFS;

public class Main {

    private static void showResult(SolutionOptimize solutionOptimize) {
        Iterable<Integer> path = solutionOptimize.path();
        int C = solutionOptimize.getC();
        System.out.print("路径为：");
        for (Integer vertex : path) {
            int vertexX = vertex / C;
            int vertexY = vertex % C;
            System.out.print("(" + vertexX + ", " + vertexY + ")-->");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("测试最基本的最短路径实现:");
        int[][] grid = {{0, 1}, {1, 0}};
        Solution solution = new Solution();
        System.out.println(solution.shortestPathBinaryMatrix(grid));
        int[][] grid2 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(solution.shortestPathBinaryMatrix(grid2));
        System.out.println();

        System.out.println("测试可以打印出完整路径的最短路径实现:");
        SolutionOptimize solutionOptimize = new SolutionOptimize();
        System.out.println(solutionOptimize.shortestPathBinaryMatrix(grid));
        showResult(solutionOptimize);
        System.out.println(solutionOptimize.shortestPathBinaryMatrix(grid2));
        showResult(solutionOptimize);
    }
}

/**
 * 结果是：
 * <p>
 * 测试最基本的最短路径实现:
 * 2
 * 4
 *
 * 测试可以打印出完整路径的最短路径实现:
 * 2
 * 路径为：(0, 0)-->(1, 1)-->
 * 4
 * 路径为：(0, 0)-->(0, 1)-->(1, 2)-->(2, 2)-->
 */
