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
        String basePath = "/Users/liangshanguang/Program/Algorithm/liuyubobobo-algorithms/Part1Basic/JAVA/src/main/java/Chapter9ShortestPath/Section3Dijkstra/";
        // 1.有负边但是无负环的图
        String filename = basePath + "graph1.txt";
        // 2.有负权边而且有负权环的图
        // String filename = basePath + "graph_with_negative_circle.txt";
        // 一定注意Bellman-Ford只能针对有向图，下面只能传入true!!!
        SparseWeightedGraph<Integer> sparseWeightedGraph = new SparseWeightedGraph<>(V, true);
        ReadWeightedGraph readWeightedGraph = new ReadWeightedGraph(sparseWeightedGraph, filename);
        sparseWeightedGraph.show();
        System.out.println("**********************Test Bellman-Ford********************");
        BellmanFord<Integer> bellmanFord = new BellmanFord<>(sparseWeightedGraph, 0);
        if (bellmanFord.negativeCycle()) {
            System.out.println("++++++++++++++++ 图中存在负边! ++++++++++++++++");
        } else {
            for (int i = 1; i < V; i++) {
                if (bellmanFord.hasPathTo(i)) {
                    System.out.println("Shortest Path To " + i + " :");
                    System.out.println("shortest weight: " + bellmanFord.shortestPathTo(i));
                    System.out.print("Path is : ");
                    bellmanFord.showPath(i);
                } else {
                    System.out.println("No Path to " + i);
                }
                System.out.println("---------------------------------------");
            }
        }
    }
}
