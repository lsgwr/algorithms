/***********************************************************
 * @Description : 基于单词查找树的无重复单词的符号表(SET实现)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/3/17 下午9:51
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter5string;

import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;

import java.util.Iterator;

public class P480TrieSET implements Iterable<String> {
    /**
     * 扩展符号表
     */
    private static final int R = 256;

    private Node root;

    /**
     * 查找表中的实时节点数
     */
    private int n;

    @Override
    public Iterator<String> iterator() {
        return keysWithPrefix("").iterator();
    }

    private static class Node {
        private Node[] next = new Node[R];
        private boolean isString;
    }

    /**
     * 初始化为空
     */
    public P480TrieSET() {
    }


    /**
     * 从指定节点x作为根节点往下查找
     *
     * @param key 给定的键
     * @param d   要查找的单词的长度
     */
    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        // 找到第d个字符对应的单词查找树
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    /**
     * 查找表中师傅包含指定的键
     */
    private boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("给定的key不能为空");
        }
        Node x = get(root, key, 0);
        if (x == null) {
            return false;
        }
        return x.isString;
    }

    /**
     * Adds the key to the set if it is not already present.
     *
     * @param key the key to add
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void add(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to add() is null");
        }
        root = add(root, key, 0);
    }

    private Node add(Node x, String key, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            if (!x.isString) {
                n++;
            }
            x.isString = true;
        } else {
            char c = key.charAt(d);
            x.next[c] = add(x.next[c], key, d + 1);
        }
        return x;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Is this symbol table empty?
     *
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }


    /**
     * 所有以prefix为前缀的键
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        P95LinkedListQueue<String> results = new P95LinkedListQueue<>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    /**
     * 收集指定前缀开头的所有单词
     *
     * @param x       指定的开始查找的节点
     * @param prefix  指定前端，为""的时候表示找到所有的键单词
     * @param results 找到的所有含指定前缀的额单词
     */
    private void collect(Node x, StringBuilder prefix, P95LinkedListQueue<String> results) {
        if (x == null) {
            return;
        }
        if (x.isString) {
            results.enqueue(prefix.toString());
        }
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * Returns all of the keys in the symbol table that match {@code pattern},
     * where . symbol is treated as a wildcard character.
     *
     * @param pattern the pattern
     * @return all of the keys in the symbol table that match {@code pattern},
     * as an iterable, where . is treated as a wildcard character.
     */
    public Iterable<String> keysThatMatch(String pattern) {
        P95LinkedListQueue<String> results = new P95LinkedListQueue<>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, P95LinkedListQueue<String> results) {
        if (x == null) {
            return;
        }
        int d = prefix.length();
        if (d == pattern.length() && x.isString) {
            results.enqueue(prefix.toString());
        }
        if (d == pattern.length()) {
            return;
        }
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * Returns the string in the symbol table that is the longest prefix of {@code query},
     * or {@code null}, if no such string.
     *
     * @param query the query string
     * @return the string in the symbol table that is the longest prefix of {@code query},
     * or {@code null} if no such string
     * @throws IllegalArgumentException if {@code query} is {@code null}
     */
    public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException("argument to longestPrefixOf() is null");
        }
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) {
            return null;
        } else {
            return query.substring(0, length);
        }
    }

    /**
     * returns the length of the longest string key in the subtrie
     * rooted at x that is a prefix of the query string,
     * assuming the first d character match and we have already
     * found a prefix match of given length (-1 if no such match)
     **/
    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) {
            return length;
        }
        if (x.isString) {
            length = d;
        }
        if (d == query.length()) {
            return length;
        }
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d + 1, length);
    }

    /**
     * Removes the key from the set if the key is present.
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.isString) {
                n--;
            }
            x.isString = false;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.isString) {
            return x;
        }
        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }
}

