/***********************************************************
 * @Description : 第10题_两个链表相加 类似第LeetCode2和445
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 9:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.第10题_两个链表相加;

import 第2章_链表问题.ListNode;

import java.math.BigDecimal;
class Solution {
    private String add2Sum(String s1, String s2) {
        BigDecimal b1 = new BigDecimal(s1);
        BigDecimal b2 = new BigDecimal(s2);
        return b1.add(b2).toString();
    }

    /**
     * 两个数相加，要考虑大数相加的情况，所以数字都以字符串的形式来存储
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 用字符串来存储两个数
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();
        while (l1 != null) {
            num1.append(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            num2.append(l2.val);
            l2 = l2.next;
        }
        String sum = add2Sum(num1.reverse().toString(), num2.reverse().toString());
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        for (int i = sum.length() - 1; i >= 0; i--) {
            cur.next = new ListNode(Integer.parseInt(sum.charAt(i) + ""));
            cur = cur.next;
        }
        return dummyHead.next;
    }
}