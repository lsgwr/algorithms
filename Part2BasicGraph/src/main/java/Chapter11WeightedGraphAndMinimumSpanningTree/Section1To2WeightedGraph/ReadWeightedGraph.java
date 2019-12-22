/***********************************************************
 * @Description : 从文件中读取内容来构造图
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 14:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadWeightedGraph {
    private static Scanner scanner;

    public static void init(WeightedGraph graph, String fileName) {
        readFile(fileName);

        try {
            int V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            }
            // 1.设置顶点数
            graph.setVertices(V);
            // 2.设置边，边数在下面的addEdge()中累计
            int E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            }
            // 读取边的顶点和权值
            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                assert v >= 0 && v < V;
                int w = scanner.nextInt();
                assert w >= 0 && w < V;
                int weight = scanner.nextInt();
                if (v==w){
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                graph.addEdge(v, w, weight);
            }
            // 3.初始化完顶点数和边数后显示下
            System.out.println(String.format("顶点数V = %d, 边数E = %d", graph.V(), graph.E()));
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from input stream, but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from input stream, but there are no more tokens available");
        }

    }

    private static void readFile(String filename) {
        assert filename != null;
        try {
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else {
                throw new IllegalArgumentException(filename + "doesn't exist.");
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + filename, ioe);
        }
    }

}
