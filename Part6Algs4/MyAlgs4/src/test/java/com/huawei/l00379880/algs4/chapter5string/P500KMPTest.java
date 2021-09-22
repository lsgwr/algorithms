package com.huawei.l00379880.algs4.chapter5string;

import org.junit.Test;

/**
 * P500KMP Tester.
 * *  % java KMP abracadabra abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern:               abracadabra
 * <p>
 * % java KMP rab abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern:         rab
 * <p>
 * % java KMP bcara abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern:                                   bcara
 * <p>
 * % java KMP rabrabracad abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern:                        rabrabracad
 * <p>
 * % java KMP abacad abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern: abacad
 *
 * @author liangshanguang
 * @date 03/18/2018
 * @description test
 */
public class P500KMPTest {

    @Test
    public void testMain() {
        String pat = "abracadabra";
        String txt = "abacadabrabracabracadabrabrabracad";
        char[] pattern = pat.toCharArray();
        char[] text = txt.toCharArray();

        P500KMP kmp1 = new P500KMP(pat);
        int offset1 = kmp1.search(txt);

        P500KMP kmp2 = new P500KMP(pattern, 256);
        int offset2 = kmp2.search(text);

        // print results
        System.out.println("text:    " + txt);

        System.out.println("偏移位置：" + offset1);
        System.out.println("偏移位置：" + offset2);
    }
} 
