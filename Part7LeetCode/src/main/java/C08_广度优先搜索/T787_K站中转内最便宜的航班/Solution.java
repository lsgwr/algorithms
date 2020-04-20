import java.util.List;
import java.util.*;

class WeightedGraph implements  Cloneable {
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
     * 邻接表，存储邻接点和邻接边的权，采用数组套map的形式；Map中键值顶点编号，值是边的权
     */
    private TreeMap<Integer, Integer>[] adj;

    public WeightedGraph(boolean directed) {
        this(0, directed);
    }

    public WeightedGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        // 泛型数组需要强制转换，可以认为是Java语言的缺陷
        adj = (TreeMap<Integer, Integer>[]) new TreeMap[vertices];
        for (int i = 0; i < vertices; i++) {
            // 每个顶点都有一组邻边组成邻接表，用TreeMap可以提高性能
            adj[i] = new TreeMap<>();
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
        // 泛型数组需要强制转换，可以认为是Java语言的缺陷
        adj = (TreeMap<Integer, Integer>[]) new TreeMap[vertices];
        for (int i = 0; i < vertices; i++) {
            // 每个顶点都有一组邻边组成邻接表，用TreeMap可以提高性能
            adj[i] = new TreeMap<>();
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
    public void addEdge(int v, int w, int weight) {
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
        adj[v].put(w, weight);
        if (!directed) {
            // 无向图实际上是双向图，所以w到v也应该为true.如果是有向图这步就不用处理了
            adj[w].put(v, weight);
        }
        // 边加1
        edges++;
    }

    /**
     * 添加边,在顶点v和顶点w之间建立一条边
     */
    public void removeEdge(int v, int w) {
        adj[v].remove(w);
        if (!directed) {
            // 无向图实际上是双向图，所以w到v也应该为true.如果是有向图这步就不用处理了
            adj[w].remove(v);
        }
        // 边减1
        edges--;
    }


    /**
     * v和w之间是否存在边
     */
    public boolean hasEdge(int v, int w) {
        // 先确保元素不越界
        validateVertex(v);
        validateVertex(w);
        // v的邻接表中是否有w
        return adj[v].containsKey(w);
    }

    public int degree(int v) {
        return adj[v].size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数V = %d, 边数E = %d，完整的邻接表信息如下：\n", vertices, edges));
        // 遍历所有顶点vertex(顶点都是按照编号顺序来地)，顶点是用从0开始的连续正整数表示时v才代表顶点，
        // 如果顶点不是用连续的正整数或者是用字符等形式来表示时，就要建立顶点数下标v和顶点实际含义的映射关系了，可以用map来表示
        // 参考 https://coding.imooc.com/learn/questiondetail/133447.html
        // vertices是vertex的复数形式，两者都是顶点的意思
        for (int v = 0; v < vertices; v++) {
            sb.append(String.format("v=%d: ", v));
            // 遍历顶点v的所有邻接点及其权重
            for (Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {
                sb.append(String.format("(w=%d, weight=%d) ", entry.getKey(), entry.getValue()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void show() {
        System.out.println(toString());
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        throw new IllegalArgumentException("边" + v + "-" + w + "不存在！");
    }

    /**
     * 设置路径v->w的权值为weight
     */
    public void setWeight(int v, int w, int weight) {
        if (!hasEdge(v, w)){
            throw new IllegalArgumentException(String.format("顶点%d和顶点%d之间没有边，无法设置边的权值!", v, w));
        }
        adj[v].put(w, weight);
        if (!directed){
            // 无向图要更新下w->v
            adj[w].put(v, weight);
        }
    }

    /**
     * 返回顶点v的所有临边
     * 由于java使用引用机制，返回一个Iterable对象不会带来额外开销,TreeSet、Vector、HashSet等都实现了Iterable接口
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        // 邻接表本身v处就是表达地v的所有邻接点
        return adj[v].keySet();
    }

    /**
     * 判断是否是有向图
     */
    public boolean isDirected() {
        return directed;
    }
}

class ShortestPathDijkstra {
    /**
     * 无向有权图
     */
    private WeightedGraph graph;
    /**
     * 求最短路径的起点，用户指定
     */
    private int start;
    /**
     * 各个定点到起始点的距离
     */
    private int[] distances;

    /**
     * 是否找到了顶点到起始点的最小距离值
     */
    private boolean[] findShortest;

    /**
     * 记录访问顺序的数组
     */
    private int[] pre;

    /**
     * 存储顶点和顶点到起始点start的最小距离值(临时和最终的)
     */
    private class Node implements Comparable<Node> {
        /**
         * 节点编号
         */
        private int v;
        /**
         * 节点v到起始点start的最小距离值(临时或最终的)
         */
        private int distance;

        public Node(int v, int distance) {
            this.v = v;
            this.distance = distance;
        }

        public int getV() {
            return v;
        }

        public int getDistance() {
            return distance;
        }

        public void setV(int v) {
            this.v = v;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(Node that) {
            return this.distance - that.distance;
        }
    }

    public ShortestPathDijkstra(WeightedGraph graph, int start) {
        graph.validateVertex(start);
        this.graph = graph;
        this.start = start;

        distances = new int[graph.V()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        // 初始化访问顺序数组
        pre = new int[graph.V()];
        Arrays.fill(pre, -1);
        // 起始点到起始点的最短距离为0
        distances[start] = 0;
        // 起始点的上一个访问节点认为是自己
        pre[start] = start;
        // 初始化所有的节点都没找到最短路径
        findShortest = new boolean[graph.V()];
        Arrays.fill(findShortest, false);
        dijkstra();
    }

    public void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            /**
             * 步骤1：确认一个顶点的距离值为到顶点的最小距离值
             */
            // 复杂度是log级别的
            int curV = pq.remove().getV();
            // 因为一个节点可能因为更新最小距离值而被加入多次，所以pq弹出节点可能弹出已经确定最小值的点，
            // 因此我们需要判断下，是地话就直接跳过
            if (findShortest[curV]) {
                continue;
            }
            // 0到curV的最短路径就确定了
            findShortest[curV] = true;

            /**
             * 步骤2：根据上面确认的最小距离值的顶点，更新起始点到其邻接点的距离值
             */
            for (int w : graph.adj(curV)) {
                if (!findShortest[w]) {
                    if (distances[curV] + graph.getWeight(curV, w) < distances[w]) {
                        distances[w] = distances[curV] + graph.getWeight(curV, w);
                        // 可能一个顶点会被加入多次，但是不影响，因为每次pq取出地都是最小值，
                        // 而我们每次加入地一个新的节点的重复值只会更小，所以取最小值的时候一定能取到我们新加入地值
                        pq.add(new Node(w, distances[w]));
                        pre[w] = curV;
                    }
                }
            }
        }
    }

    /**
     * 获取起始点start到顶点v的最小距离值
     */
    public int[] getDistances() {
        return distances;
    }

    /**
     * 判断顶点v到定点start之间是否连通
     */
    public boolean isConnectedTo(int v) {
        graph.validateVertex(v);
        return findShortest[v];
    }

    /**
     * 起点start到定点v的最小距离值
     */
    public int shortestDistanceTo(int v) {
        graph.validateVertex(v);
        return distances[v];
    }

    /**
     * 获取起始点到顶点v的最短路劲轨迹
     */
    public Iterable<Integer> getPath(int v) {
        List<Integer> path = new ArrayList<>();
        if (!isConnectedTo(v)) {
            return path;
        }
        int cur = v;
        while (cur != start) {
            path.add(cur);
            cur = pre[cur];
        }
        // 加入起始点
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}

class Solution {
    /**
     * 有向有权图的最短路径问题，Dijkstra算法
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // 初始化为有向图
        WeightedGraph graph = new WeightedGraph(true);
        // 顶点数,n个城市
        graph.setVertices(n);
        for (int[] flight : flights) {
            graph.addEdge(flight[0], flight[1], flight[2]);
        }
        ShortestPathDijkstra dijkstra = new ShortestPathDijkstra(graph, src);
        List<Integer> path = (List<Integer>) dijkstra.getPath(dst);
        while (path.size() > K+2){
            // 比限制的中转站要多
            graph.removeEdge(path.get(0), path.get(1));
            dijkstra = new ShortestPathDijkstra(graph, src);
            path = (List<Integer>) dijkstra.getPath(dst);
        }
        return dijkstra.shortestDistanceTo(dst) == Integer.MAX_VALUE ? -1:dijkstra.shortestDistanceTo(dst);
    }

    // public static void main(String[] args) {
    //     int n = 3;
    //     int[][] edges = {{0,1,100},{1,2,100},{0,2,500}};
    //     int src = 0;
    //     int dst = 2;
    //     int K = 1;
    //     System.out.println(new Solution().findCheapestPrice(n, edges,src, dst, K));
    // }
}