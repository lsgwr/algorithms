/***********************************************************
 * @Description : 297. 二叉树的序列化与反序列化
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
        StringBuilder res = ser_help(root, new StringBuilder());
        return res.toString();
    }

    public StringBuilder ser_help(TreeNode root, StringBuilder str){
        if(null == root){
            str.append("null,");
            return str;
        }
        str.append(root.val);
        str.append(",");
        str = ser_help(root.left, str);
        str = ser_help(root.right, str);
        return str;
    }

    public TreeNode deserialize(String data) {
        String[] str_word = data.split(",");
        List<String> list_word = new LinkedList<String>(Arrays.asList(str_word));
        return deser_help(list_word);
    }

    public TreeNode deser_help(List<String> li){
        if(li.get(0).equals("null")){
            li.remove(0);
            return null;
        }
        TreeNode res = new TreeNode(Integer.valueOf(li.get(0)));
        li.remove(0);
        res.left = deser_help(li);
        res.right = deser_help(li);
        return res;
    }
}

