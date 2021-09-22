/***********************************************************
 * @Description : :LeetCode LCP4.覆盖 多米诺骨牌问题
 * https://leetcode-cn.com/problems/broken-board-dominoes/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/27 18:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter15Matching.Section5to7Hungarian;

import java.util.*;
class Graph implements Cloneable {
    /**
     * 图的顶点数
     */
    private int vertices;
    /**
     * 图的边数
     */
    private int edges;
    /**
     * 当前图是有向图还是无向图
     */
    private boolean directed;
    /**
     * 邻接表，采用vector套vector的形式
     */
    private TreeSet<Integer>[] adj;
    /**
     * 所有顶点的入度
     */
    private int[] inDegrees;
    /**
     * 所有顶点的出度
     */
    private int[] outDegrees;

    public Graph(boolean directed) {
        this(0, directed);
    }

    public Graph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        inDegrees = new int[vertices];
        outDegrees = new int[vertices];
        // 泛型数组需要强制转换，可以认为是Java语言的缺陷
        adj = (TreeSet<Integer>[]) new TreeSet[vertices];
        for (int i = 0; i < vertices; i++) {
            // 每个顶点都有一组邻边组成邻接表，用TreeSet可以提高性能
            adj[i] = new TreeSet<>();
        }
    }


    /**
     * 返回顶点数
     */
    public int V() {
        return vertices;
    }

    /**
     * 设置图的顶点数
     *
     * @param vertices 顶点数
     */
    public void setVertices(int vertices) {
        this.vertices = vertices;
        inDegrees = new int[vertices];
        outDegrees = new int[vertices];
        // 泛型数组需要强制转换，可以认为是Java语言的缺陷
        adj = (TreeSet<Integer>[]) new TreeSet[vertices];
        for (int i = 0; i < vertices; i++) {
            // 每个顶点都有一组邻边组成邻接表，用TreeSet可以提高性能
            adj[i] = new TreeSet<>();
        }
    }

    /**
     * 返回边的数目
     */
    public int E() {
        return edges;
    }

    public void validateVertex(int v) {
        assert (v >= 0 && v < vertices);
    }

    /**
     * 添加边,在顶点v和顶点w之间建立一条边
     */
    public void addEdge(int v, int w) {
        // 先确保元素不越界
        validateVertex(v);
        validateVertex(w);
        // v和w之间是连接地,不需要再加一次，就直接退出。这样是为了防止平行边，但是从hasEdge实现可知成本较高，所以就by了
        // 平行边可以在所有边加完之后统一去掉，自己实现
        // if (hasEdge(v, w)) {
        //    return;
        // }

        // v=w会生成自环边
        if (v == w) {
            throw new IllegalArgumentException("Self Loop is Detected!");
        }
        adj[v].add(w);
        if (!directed) {
            // 无向图实际上是双向图，所以w到v也应该为true.如果是有向图这步就不用处理了
            adj[w].add(v);
        } else {
            // 有向图，入度和出度都要更新，因为是v-->w，所以v的出度+1，w的入度+1
            outDegrees[v]++;
            inDegrees[w]++;
        }
        // 边加1
        edges++;
    }

    /**
     * v和w之间是否存在边
     */
    public boolean hasEdge(int v, int w) {
        // 先确保元素不越界
        validateVertex(v);
        validateVertex(w);
        // v的邻接表中是否有w
        return adj[v].contains(w);
    }

    public int degree(int v) {
        // Todo:针对有向图的度比较麻烦，第13章最后讲
        if (directed) {
            throw new RuntimeException("degree()方法仅能用于无向图");
        }
        return adj[v].size();
    }

    /**
     * 顶点v的入度(其他边指向v的条数)
     */
    public int inDegree(int v) {
        if (!directed){
            throw new RuntimeException("inDegree()方法仅适用于有向图");
        }
        validateVertex(v);
        return inDegrees[v];
    }

    /**
     * 顶点v的出度(从v指向其他顶点的边数)
     */
    public int outDegree(int v) {
        if (!directed){
            throw new RuntimeException("outDegree()方法仅适用于有向图");
        }
        validateVertex(v);
        return outDegrees[v];
    }

    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (adj[v].contains(w)){
            // 边数-1
            edges--;
            // v的邻接点包含w才进行删除边
            adj[v].remove(w);
            if (!directed) {
                // 无向图才需要删除边w-v
                adj[w].remove(v);
            }else {
                // 有向图需要更新入度和出度.v->w所以v的出度-1，w的入度-1
                outDegrees[v]--;
                inDegrees[w]--;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数V = %d, 边数E = %d\n", vertices, edges));
        // 遍历所有顶点vertex(顶点都是按照编号顺序来地)，顶点是用从0开始的连续正整数表示时v才代表顶点，
        // 如果顶点不是用连续的正整数或者是用字符等形式来表示时，就要建立顶点数下标v和顶点实际含义的映射关系了，可以用map来表示
        // 参考 https://coding.imooc.com/learn/questiondetail/133447.html
        // vertices是vertex的复数形式，两者都是顶点的意思
        for (int v = 0; v < vertices; v++) {
            sb.append(String.format("vertex %d:\t", v));
            // 遍历顶点vertex的所有邻接点
            for (Integer w : adj[v]) {
                sb.append(String.format("%d\t", w));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void show() {
        System.out.println(toString());
    }


    /**
     * 返回顶点v的所有临边
     * 由于java使用引用机制，返回一个Iterable对象不会带来额外开销,TreeSet、Vector、HashSet等都实现了Iterable接口
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        // 邻接表本身v处就是表达地v的所有邻接点
        return adj[v];
    }

    /**
     * 实现Graph对象的深拷贝(adj要完成拷贝过去)
     *
     * @return 深拷贝后的graph，在调用时不会改变原对象
     * @throws CloneNotSupportedException 不支持Clone的异常
     */
    @Override
    public Object clone() {
        // 实现深拷贝
        Graph cloned = null;
        try {
            cloned = (Graph) super.clone();
            cloned.adj = new TreeSet[vertices];
            for (int v = 0; v < vertices; v++) {
                cloned.adj[v] = new TreeSet<>();
                for (int w : adj[v]) {
                    cloned.adj[v].add(w);
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // 出异常了就返回null
        return null;
    }

    /**
     * 返回图片是否是有向图
     */
    public boolean isDirected() {
        return directed;
    }
}

class GraphDFSBiPartitionDetect {
    private Graph graph;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();

    /**
     * 颜色数组，存储每个节点的颜色
     */
    private int[] colors;

    /**
     * 是否是二分图,默认成是二分图
     */
    private boolean biPartition = true;

    public GraphDFSBiPartitionDetect(Graph graph) {
        this.graph = graph;
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[graph.V()];
        // 初始化颜色数组
        this.colors = new int[graph.V()];
        // 初始化colors为-1，后面检测到二分图会更新这个数组，会有0和1两种
        Arrays.fill(this.colors, -1);
        // 从dfs(0)改成下面的代码，可以支持非连通的图,不用考虑连通分量的时候直接用dfs(v)即可
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                // 第一个节点染成蓝色(0)
                if (!dfs(v, 0)) {
                    // 某一个联通分量不是二分图，整个图就不是二分图了，直接返回，不再检测剩下的二分图了
                    biPartition = false;
                    // 一旦检测到二分图立马跳出，一定别忘
                    break;
                }
            }
        }
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    /**
     * 是否是二分图
     */
    public boolean isBiPartition() {
        return biPartition;
    }

    /**
     * 获取图划分后的二分图数组
     */
    public int[] getColors() {
        return colors;
    }

    /**
     * dfs过程中检测当前图是否是二分图
     *
     * @param v     当前的顶点
     * @param color v点的染色
     * @return 是否是二分图
     */
    private boolean dfs(int v, int color) {
        visited[v] = true;
        orderList.add(v);
        colors[v] = color;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                // 颜色只有蓝(0)、绿(1)两种，w是v的邻接点，根据二分图的检测原理，w、v的颜色必须相反，只能一蓝一绿，蓝+绿 = 0 + 1 = 1,所以1-v的颜色 = 1-color = w的颜色
                if (!dfs(w, 1 - color)) {
                    // 返回false表示不是二分图
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                // 如果w已经访问过，但是w作为v的邻接点和v的颜色相同，说明不是二分图
                return false;
            }
        }
        return true;
    }
}
class Hungarian {
    /**
     * 要找最大匹配的二分图
     */
    private Graph graph;
    /**
     * 最大匹配的值
     */
    private int maxMatch = 0;

    /**
     * match[v]=w表示顶点v在图中匹配的顶点是w
     */
    private int[] matching;

    public Hungarian(Graph graph) {
        GraphDFSBiPartitionDetect biPartitionDetect = new GraphDFSBiPartitionDetect(graph);
        if (!biPartitionDetect.isBiPartition()) {
            throw new IllegalArgumentException("匹配问题必须针对地是二分图!");
        }
        this.graph = graph;
        // 二分图中的顶点颜色区分，一半为0(左侧)，一半为1(右侧)
        int[] colors = biPartitionDetect.getColors();
        int V = graph.V();
        matching = new int[V];
        Arrays.fill(matching, -1);
        for (int v = 0; v < V; v++) {
            // 只遍历二分图中左侧还未被遍历的点(颜色为0)
            if (colors[v] == 0 && matching[v] == -1) {
                // 每个左侧的点都进行一次bfs来找增广路径
                if (bfs(v)) {
                    // 本地bfs找到了一条增广路径
                    maxMatch++;
                }
            }
        }
    }

    /**
     * bfs找增广路径
     *
     * @param source bfs遍历的起点
     * @return 本次bfs是否找到了增广路径
     */
    private boolean bfs(int source) {
        Queue<Integer> queue = new ArrayDeque<>();
        int V = graph.V();
        // 记录节点访问顺序的数组，每次bfs都需要新建自己的pre
        int[] pre = new int[V];
        Arrays.fill(pre, -1);
        queue.add(source);
        // 起点的上一个访问节点认为是自己
        pre[source] = source;
        while (!queue.isEmpty()) {
            // v一定要用二分图左侧的顶点(本类中0标记)
            int v = queue.remove();
            for (int w : graph.adj(v)) {
                // w是二分图右侧的顶点
                if (pre[w] == -1) {
                    if (matching[w] != -1) {
                        // w已经匹配
                        pre[w] = v;
                        // 和w匹配的边的前一条边记为w
                        pre[matching[w]] = w;
                        // 添加左侧的点到queue中，queue中只存左侧的点
                        queue.add(matching[w]);
                    } else {
                        // 在二分图右侧找到了一个未匹配的点，即找到了增广路径
                        pre[w] = v;
                        List<Integer> augPath = getAugPath(pre, v, w);
                        // 匈牙利算法核心：匹配状态取反。即匹配边变非匹配边、非匹配边变匹配边
                        for (int i = 0; i < augPath.size(); i += 2) {
                            matching[augPath.get(i)] = augPath.get(i + 1);
                            matching[augPath.get(i + 1)] = augPath.get(i);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取增广路径详情
     *
     * @param pre   节点访问顺序数组
     * @param start 路径起点
     * @param end   路径终点
     * @return 路径上的点的顺序列表
     */
    private List<Integer> getAugPath(int[] pre, int start, int end) {

        List<Integer> path = new ArrayList<>();
        int cur = end;
        while (cur != start) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(start);
        // 增广路径无论正反都是增广路径，所以这里逆序与否均可了
        Collections.reverse(path);
        return path;
    }

    /**
     * 获取最大匹配数
     */
    public int getMaxMatch() {
        return maxMatch;
    }

    /**
     * 当前的最大匹配是否是完全匹配(也可以叫完全匹配，即所有的点都有二分图另一侧的点和自己匹配而且匹配对互不干扰O)
     */
    public boolean isPrefectMatch() {
        return maxMatch * 2 == graph.V();
    }
}

public class Solution {
    /**
     * 输入：n, m代表棋盘的大小；
     *
     * @param n      棋盘的宽度
     * @param m      棋盘的高度
     * @param broken broken是一个b * 2的二维数组，其中每个元素代表棋盘上每一个坏掉的格子的位置
     * @return 最多能放多少个多米诺骨牌
     */
    public int domino(int n, int m, int[][] broken) {
        int[][] board = new int[n][m];
        for (int[] p : broken) {
            // 把坏掉的格子置为1
            board[p[0]][p[1]] = 1;
        }
        // 初始化一个无向图，这次不是从文件里服读取了，自己从board中进行取值构造Graph
        Graph graph = new Graph(n * m, false);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j + 1 < m && board[i][j] == 0 && board[i][j + 1] == 0) {
                    // 下面是二维坐标转一维坐标
                    int v = i * m + j;
                    int w = i * m + (j + 1);
                    graph.addEdge(v, w);
                }
                if (i + 1 < n && board[i][j] == 0 && board[i + 1][j] == 0) {
                    // 下面是二维坐标转一维坐标
                    int v = i * m + j;
                    int w = (i + 1) * m + j;
                    graph.addEdge(v, w);
                }
            }
        }

        // 利用最大匹配的类求上面二分图graph
        Hungarian maxMatching = new Hungarian(graph);
        return maxMatching.getMaxMatch();
    }

    public static void main(String[] args) {
        int n = 2, m = 3;
        int[][] broken = {{1, 0}, {1, 1}};
        Solution solution = new Solution();
        System.out.println("最多能放" + solution.domino(n, m, broken) + "块多米诺骨牌");
    }
}
/**
 * 最多能放2块多米诺骨牌
 */