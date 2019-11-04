package com.huawei.l00379880.algs4.chapter5string;

/***********************************************************
 * @Description : 字符表
 * @author      : 梁山广
 * @date        : 2018/2/25 17:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P453Alphabet {
    /**
     * The binary alphabet { 0, 1 }.
     */
    public static final P453Alphabet BINARY = new P453Alphabet("01");

    /**
     * The octal alphabet { 0, 1, 2, 3, 4, 5, 6, 7 }.
     */
    public static final P453Alphabet OCTAL = new P453Alphabet("01234567");

    /**
     * The decimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }.
     */
    public static final P453Alphabet DECIMAL = new P453Alphabet("0123456789");

    /**
     * The hexadecimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F }.
     */
    public static final P453Alphabet HEXADECIMAL = new P453Alphabet("0123456789ABCDEF");

    /**
     * The DNA alphabet { A, C, T, G }.
     */
    public static final P453Alphabet DNA = new P453Alphabet("ACGT");

    /**
     * The lowercase alphabet { a, b, c, ..., z }.
     */
    public static final P453Alphabet LOWERCASE = new P453Alphabet("abcdefghijklmnopqrstuvwxyz");

    /**
     * The uppercase alphabet { A, B, C, ..., Z }.
     */

    public static final P453Alphabet UPPERCASE = new P453Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    /**
     * The protein alphabet { A, C, D, E, F, G, H, I, K, L, M, N, P, Q, R, S, T, V, W, Y }.
     */
    public static final P453Alphabet PROTEIN = new P453Alphabet("ACDEFGHIKLMNPQRSTVWY");

    /**
     * The base-64 alphabet (64 characters).
     */
    public static final P453Alphabet BASE64 = new P453Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    /**
     * The ASCII alphabet (0-127).
     */
    public static final P453Alphabet ASCII = new P453Alphabet(128);

    /**
     * The extended ASCII alphabet (0-255).
     */
    public static final P453Alphabet EXTENDED_ASCII = new P453Alphabet(256);

    /**
     * The Unicode 16 alphabet (0-65,535).
     */
    public static final P453Alphabet UNICODE16 = new P453Alphabet(65536);
    /**
     * 字符表里的字符
     */
    private char[] alphabet;
    /**
     * 索引
     */
    private int[] inverse;
    /**
     * 字符表的基数(radix)
     */
    private final int R;

    /**
     * Initializes a new alphabet from the given set of characters
     *
     * @param alpha
     */
    public P453Alphabet(String alpha) {
        // 检查字符表中是否有重复字符
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < alpha.length(); i++) {
            char c = alpha.charAt(i);
            if (unicode[c]) {
                throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
            }
            unicode[c] = true;
        }
        // 字符串转换为字符数组
        alphabet = alpha.toCharArray();
        R = alpha.length();
        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++) {
            // 初始化索引值
            inverse[i] = -1;
        }
        // 不能用char类型,因为R最大可以为65536
        // 给索引值赋值
        for (int c = 0; c < R; c++) {
            inverse[alphabet[c]] = c;
        }
    }

    public P453Alphabet(int radix) {
        this.R = radix;
        alphabet = new char[R];
        inverse = new int[R];
        // 不能用char类型,因为R最大可以为65536
        for (int i = 0; i < R; i++) {
            alphabet[i] = (char) i;
            inverse[i] = i;
        }
    }

    public P453Alphabet() {
        this(256);
    }

    /**
     * 字符表中是否包含字符c
     */
    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    /**
     * 获取字符表的基数
     */
    public int getRadix() {
        return R;
    }

    /**
     * 获取R的以2位底的对数
     */
    public int lgR() {
        int lgR = 0;
        for (int t = R - 1; t >= 1; t /= 2) {
            lgR++;
        }
        return lgR;
    }

    /**
     * 获取字符c在字符表里对应的索引
     */
    public int toIndex(char c) {
        if (c >= inverse.length || inverse[c] == -1) {
            throw new IllegalArgumentException("字符" + c + "不在字符表里!");
        }
        return inverse[c];
    }

    /**
     * 获取一个字符串里所有的字符对应的索引数组
     */
    public int[] toIndices(String str) {
        char[] source = str.toCharArray();
        int[] target = new int[str.length()];
        for (int i = 0; i < source.length; i++) {
            target[i] = toIndex(source[i]);
        }
        return target;
    }

    public char toChar(int index) {
        if (index < 0 || index >= R) {

            throw new IllegalArgumentException("字符索引必须在0到" + R + "之间");
        }
        return alphabet[index];
    }

    /**
     * 获取索引数组对应的字符数组即字符串
     */
    public String toChars(int[] indices) {
        StringBuilder sb = new StringBuilder(indices.length);
        for (int i = 0; i < indices.length; i++) {
            sb.append(toChar(indices[i]));
        }
        return sb.toString();
    }


}
