package com.huawei.l00379880.algs4.chapter5string;

import org.junit.Test;

/**
 * P504BoyerMoore Tester.
 * *  % java BoyerMoore abracadabra abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern:               abracadabra
 * <p>
 * % java BoyerMoore rab abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern:         rab
 * <p>
 * % java BoyerMoore bcara abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern:                                   bcara
 * <p>
 * % java BoyerMoore rabrabracad abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern:                        rabrabracad
 * <p>
 * % java BoyerMoore abacad abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern: abacad
 *
 * @author liangshanguang
 * @date 03/18/2018
 * @description test
 */
public class P504BoyerMooreTest {

    @Test
    public void testMain() {
        String pat = "abracadabra";
        String txt = "abacadabrabracabracadabrabrabracad";
        char[] pattern = pat.toCharArray();
        char[] text = txt.toCharArray();

        P504BoyerMoore boyerMoore1 = new P504BoyerMoore(pat);
        int offset1 = boyerMoore1.search(txt);

        P504BoyerMoore boyerMoore2 = new P504BoyerMoore(pattern, 256);
        int offset2 = boyerMoore2.search(text);

        // print results
        System.out.println("text:    " + txt);

        System.out.println("偏移位置：" + offset1);
        System.out.println("偏移位置：" + offset2);
    }
} 
