/***********************************************************
 * @Description : 从文件中读取内容来构造图
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 14:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Section4ReadGraph;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadGraph {
    private Scanner scanner;

    public ReadGraph(Graph graph, String fileName) {
        readFile(fileName);

        try {
            int V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            }
            assert V == graph.V();

            int E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            }

            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                assert v >= 0 && v < V;
                assert w >= 0 && w < V;
                graph.addEdge(v, w);
            }
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from input stream, but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from input stream, but there are no more tokens available");
        }

    }

    private void readFile(String filename) {
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
