/***********************************************************
 * @Description : 297. 二叉树的序列化与反序列化 实际是二叉树的层序遍历
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/22 08:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07BSTAndRecursion;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Codec {

    public String serialize(TreeNode root) {      //用StringBuilder
        StringBuilder res = serializeHelp(root, new StringBuilder());
        return res.toString();
    }

    public StringBuilder serializeHelp(TreeNode root, StringBuilder str) {
        if (null == root) {
            str.append("null,");
            return str;
        }
        str.append(root.val);
        str.append(",");
        str = serializeHelp(root.left, str);
        str = serializeHelp(root.right, str);
        return str;
    }

    public TreeNode deserialize(String data) {
        String[] wordsStr = data.split(",");
        List<String> wordList= new LinkedList<>(Arrays.asList(wordsStr));
        return deserializeHelp(wordList);
    }

    public TreeNode deserializeHelp(List<String> li) {
        if (li.size() == 0) {
            return null;
        }

        if (li.get(0).equals("null")) {
            li.remove(0);
            return null;
        }
        TreeNode res = new TreeNode(Integer.valueOf(li.get(0)));
        li.remove(0);
        res.left = deserializeHelp(li);
        res.right = deserializeHelp(li);
        return res;
    }
}
