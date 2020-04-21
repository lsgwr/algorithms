/***********************************************************
 * @Description : 杨树林抑制剂
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/20 20:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/

// We have imported the necessary tool classes.
// If you need to import additional packages or classes, please import here.

import java.util.*;

/**
 * 看从哪个节点开始的BFS耗时最短
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        TreeNode root = get5wTree(line);
        if (root == null) {
            root = getTree(line);
        }
        System.out.println(spread(root));
    }

    private static int spread(TreeNode root) {
        // 请在此完成代码
        if (root == null) {
            return 0;
        }
        // 记录每个节点和其邻接节点的map
        Map<Integer, List<Integer>> nodeMap = new HashMap<>();

        // 层序遍历所有节点，完善点和其邻接点的map映射关系
        Queue<TreeNode> queue = new ArrayDeque<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            List<Integer> adj = new ArrayList<>();
            if (parentMap.get(node.val) != null) {
                adj.add(parentMap.get(node.val));
            }
            if (node.left != null) {
                queue.add(node.left);
                adj.add(node.left.val);
                parentMap.put(node.left.val, node.val);
            }
            if (node.right != null) {
                queue.add(node.right);
                adj.add(node.right.val);
                parentMap.put(node.right.val, node.val);
            }
            // 更新邻接点的map
            nodeMap.put(node.val, adj);
        }

        // bfs遍历所有叶子节点，不断更新遍历遍历完所有节点后的最小耗时
        int minTimeCost = Integer.MAX_VALUE;

        for (Integer key : nodeMap.keySet()) {
            List<Integer> adj = nodeMap.get(key);
            if (adj.size() == 1) {
                // 邻接点只有一个，说明是叶子节点，从这个点开始进行注射抑制剂
                Map<Integer, Boolean> nodeVisited = new HashMap<>();
                int timeCost = 0;
                List<Integer> injectList = new ArrayList<>();
                injectList.add(key);
                // 起点先设置为访问
                nodeVisited.put(key, true);
                while (injectList.size() != 0) {
                    List<Integer> newInjectList = new ArrayList<>();
                    // 把下一层的节点加入到newInjectList，方便下一轮访问
                    for (int node : injectList) {
                        // 遍历所有node的邻接点
                        for (int nodeNext : nodeMap.get(node)) {
                            if (nodeVisited.get(nodeNext) == null){
                                // 点没被访问过，加入到下一轮访问列表中
                                newInjectList.add(nodeNext);
                                nodeVisited.put(nodeNext, true);
                            }
                        }
                    }
                    if (newInjectList.size() == 0){
                        break;
                    }
                    injectList = new ArrayList<>(newInjectList);
                    timeCost++;
                }
                minTimeCost = Math.min(timeCost, minTimeCost);
            }
        }
        return minTimeCost;
    }

    /******************注：以下代码请勿修改 ********/
    private static TreeNode get5wTree(String line) {
        TreeNode root = null;
        if (line.equals("5w1")) {
            root = TreeNode.build5wInLine1();
        } else if (line.equals("5w2")) {
            root = TreeNode.build5wInLine2();
        } else if (line.equals("5w3")) {
            root = TreeNode.build5wFull();
        }
        return root;
    }

    private static TreeNode getTree(String line) {
        String[] datas = line.split(",");
        TreeNode[] nodesArray = Arrays.stream(datas).map(value -> {
            if (value.equals("null")) {
                return null;
            }
            return new TreeNode(Integer.parseInt(value));
        }).toArray(TreeNode[]::new);
        for (int i = 0; i < nodesArray.length; i++) {
            if (2 * i + 1 >= nodesArray.length) {
                break;
            }
            if (nodesArray[i] != null) {
                nodesArray[i].left = nodesArray[2 * i + 1]; // 左子节点索引为 2*index+1
                nodesArray[i].right = 2 * i + 2 >= nodesArray.length ? null : nodesArray[2 * i + 2];  // 右子节点索引为 2*index+1
            }
        }
        return nodesArray[0];
    }
}

/**
 * 请不要修改此类
 ***/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static TreeNode build5wInLine1() {
        TreeNode root = new TreeNode(1);
        int nodeNum = 1;
        TreeNode rootTmp = root;
        while (nodeNum++ < 50000) {
            TreeNode child = new TreeNode(2 * (root.val - 1) + 2 + 1);
            root.right = child;
            root = child;
        }
        return rootTmp;
    }

    public static TreeNode build5wInLine2() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        int nodeNum = 3;
        while (nodeNum < 50000) {
            TreeNode leftChild = new TreeNode(2 * (left.val - 1) + 1 + 1);
            left.left = leftChild;
            left = leftChild;
            nodeNum++;
            if (nodeNum < 50000) {
                TreeNode rightChild = new TreeNode(2 * (right.val - 1) + 2 + 1);
                right.right = rightChild;
                right = rightChild;
                nodeNum++;
            }
        }
        return root;
    }

    public static TreeNode build5wFull() {
        TreeNode[] nodesArray = new TreeNode[50000];
        for (int i = 0; i < nodesArray.length; i++) {
            nodesArray[i] = new TreeNode(i + 1);
        }
        for (int i = 0; i < nodesArray.length; i++) {
            if (2 * i + 1 >= nodesArray.length) {
                break;
            }
            if (nodesArray[i] != null) {
                nodesArray[i].left = nodesArray[2 * i + 1]; // 左子节点索引为 2*index+1
                nodesArray[i].right = 2 * i + 2 >= nodesArray.length ? null : nodesArray[2 * i + 2];  // 右子节点索引为 2*index+1
            }
        }
        return nodesArray[0];
    }
}