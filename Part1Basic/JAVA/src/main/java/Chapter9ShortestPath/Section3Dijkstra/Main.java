/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/12 22:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter9ShortestPath.Section3Dijkstra;

public class Main {
    public static void main(String[] args) {
        int V = 5;
        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "/Users/liangshanguang/Program/Algorithm/liuyubobobo-algorithms/Part1Basic/JAVA/src/main/java/Chapter9ShortestPath/Section3Dijkstra/graph1.txt";
        SparseWeightedGraph sparseWeightedGraph = new SparseWeightedGraph(5, false);
        ReadWeightedGraph readWeightedGraph1 = new ReadWeightedGraph(sparseWeightedGraph, filename);
        System.out.println("**********************Test Dijkstra********************");
        Dijkstra<Integer> dijkstra = new Dijkstra<>(sparseWeightedGraph, 0);
        for (int i = 1; i < V; i++) {
            if (dijkstra.hasPathTo(i)) {
                System.out.println("Shortest Path To " + i + " :");
                System.out.println("shortest weight: " + dijkstra.shortestPathTo(i));
                System.out.print("Path is : ");
                dijkstra.showPath(i);
            } else {
                System.out.println("No Path to " + i);
            }
            System.out.println("---------------------------------------");
        }
    }
}
