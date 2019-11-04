/***********************************************************
 * @Description : 指纹字符串查找算法(蒙特卡罗算法)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/3/18 下午12:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter5string;

import java.math.BigInteger;
import java.util.Random;

public class P508RabinKarp {
    /**
     * the pattern  // needed only for Las Vegas
     */
    private String pat;
    /**
     * pattern hash value
     */
    private long patHash;
    /**
     * pattern length
     */
    private int m;
    /**
     * a large prime, small enough to avoid long overflow
     */
    private long q;
    /**
     * radix
     */
    private int R;
    /**
     * R^(M-1) % Q
     */
    private long RM;

    /**
     * Preprocesses the pattern string.
     *
     * @param pattern the pattern string
     * @param R       the alphabet size
     */
    public P508RabinKarp(char[] pattern, int R) {
        this.pat = String.valueOf(pattern);
        this.R = R;
        throw new UnsupportedOperationException("Operation not supported yet");
    }

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public P508RabinKarp(String pat) {
        // save pattern (needed only for Las Vegas)
        this.pat = pat;
        R = 256;
        m = pat.length();
        q = longRandomPrime();

        // precompute R^(m-1) % q for use in removing leading digit
        RM = 1;
        for (int i = 1; i <= m - 1; i++) {
            RM = (R * RM) % q;
        }
        patHash = hash(pat, m);
    }

    /**
     * Compute hash for key[0..m-1].
     */
    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++) {
            h = (R * h + key.charAt(j)) % q;
        }
        return h;
    }

    /**
     * Las Vegas version: does pat[] match txt[i..i-m+1] ?
     */
    private boolean check(String txt, int i) {
        for (int j = 0; j < m; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param txt the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; n if no such match
     */
    public int search(String txt) {
        int n = txt.length();
        if (n < m) {
            return n;
        }
        long txtHash = hash(txt, m);

        // check for match at offset 0
        if ((patHash == txtHash) && check(txt, 0)) {
            return 0;
        }

        // check for hash match; if hash match, check for exact match
        for (int i = m; i < n; i++) {
            // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + q - RM * txt.charAt(i - m) % q) % q;
            txtHash = (txtHash * R + txt.charAt(i)) % q;

            // match
            int offset = i - m + 1;
            if ((patHash == txtHash) && check(txt, offset)) {
                return offset;
            }
        }

        // no match
        return n;
    }


    /**
     * a random 31-bit prime
     */
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

}
