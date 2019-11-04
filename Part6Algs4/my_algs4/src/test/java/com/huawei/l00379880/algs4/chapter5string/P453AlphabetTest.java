package com.huawei.l00379880.algs4.chapter5string;

import org.junit.Test;

/**
 * P453Alphabet Tester.
 *
 * @author liang shan guang
 * @datetime 02/25/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P453AlphabetTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        int[] encode1 = P453Alphabet.BASE64.toIndices("liangshanguang");
        String decode1 = P453Alphabet.BASE64.toChars(encode1);
        System.out.println(decode1);


        int[] encoded2 = P453Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = P453Alphabet.DNA.toChars(encoded2);
        System.out.println(decoded2);

        int[] encoded3 = P453Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = P453Alphabet.DECIMAL.toChars(encoded3);
        System.out.println(decoded3);
    }

    /**
     * 统计字符在字符串中出现的次数
     */
    @Test
    public void testMain2() {
        P453Alphabet alphabet = new P453Alphabet("ABCDR");
        int R = alphabet.getRadix();
        int[] count = new int[R];
        String str = "ABRACADABRA!";
        int N = str.length();
        for (int i = 0; i < N; i++) {
            if (alphabet.contains(str.charAt(i))) {
                count[alphabet.toIndex(str.charAt(i))]++;
            }
        }

        for (int c = 0; c < R; c++) {
            System.out.println(alphabet.toChar(c) + " " + count[c]);
        }
    }
} 
