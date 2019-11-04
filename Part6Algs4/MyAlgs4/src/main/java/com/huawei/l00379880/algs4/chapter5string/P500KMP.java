/***********************************************************
 * @Description : Knuth-Morris-Pratt算法(字符串查找和匹配)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/3/18 上午10:51
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter5string;

public class P500KMP {
    private final int R;
    /**
     * 状态自动机
     */
    private int[][] dfa;
    /**
     * 待匹配字符串的字符数组
     */
    private char[] pattern;
    /**
     * 待匹配字符串
     */
    private String pat;

    public P500KMP(String pat) {
        // 初始化字符表
        this.R = 256;
        this.pat = pat;

        // build DFA from pattern
        int m = pat.length();
        dfa = new int[R][m];
        dfa[pat.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++) {
                // Copy mismatch cases.
                dfa[c][j] = dfa[c][x];
            }
            // Set match case.
            dfa[pat.charAt(j)][j] = j + 1;
            // Update restart state.
            x = dfa[pat.charAt(j)][x];
        }
    }

    public P500KMP(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++) {
            this.pattern[j] = pattern[j];
        }
        // build DFA from pattern
        int m = pattern.length;
        dfa = new int[R][m];
        dfa[pattern[0]][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++) {
                // Copy mismatch cases.
                dfa[c][j] = dfa[c][x];
            }
            // Set match case.
            dfa[pattern[j]][j] = j + 1;
            // Update restart state.
            x = dfa[pattern[j]][x];
        }
    }

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param txt the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; N if no such match
     */
    public int search(String txt) {

        // simulate operation of DFA on text
        int m = pat.length();
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        // found
        if (j == m) {
            return i - m;
        }
        // not found
        return n;
    }

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param text the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; N if no such match
     */
    public int search(char[] text) {

        // simulate operation of DFA on text
        int m = pattern.length;
        int n = text.length;
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[text[i]][j];
        }
        // found
        if (j == m) {
            return i - m;
        }
        // not found
        return n;
    }

}
