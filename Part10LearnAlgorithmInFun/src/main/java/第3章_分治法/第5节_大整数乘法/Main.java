/***********************************************************
 * @Description : 大整数乘法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/29 15:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第3章_分治法.第5节_大整数乘法;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        BigInteger result = new BigInteger(a).multiply(new BigInteger(b));
        System.out.println(result.toString());
    }
}
/**
 * 15241578750190521
 */
