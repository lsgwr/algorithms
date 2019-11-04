package com.huawei.l00379880.algs4.chapter5string;

import org.junit.Test;

/**
 * P508RabinKarp Tester.
 * *  % java RabinKarp abracadabra abacadabrabracabracadabrabrabracad
 * pattern: abracadabra
 * text:    abacadabrabracabracadabrabrabracad
 * match:                 abracadabra
 * <p>
 * % java RabinKarp rab abacadabrabracabracadabrabrabracad
 * pattern: rab
 * text:    abacadabrabracabracadabrabrabracad
 * match:           rab
 * <p>
 * % java RabinKarp bcara abacadabrabracabracadabrabrabracad
 * pattern: bcara
 * text:         abacadabrabracabracadabrabrabracad
 * <p>
 * %  java RabinKarp rabrabracad abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern:                        rabrabracad
 * <p>
 * % java RabinKarp abacad abacadabrabracabracadabrabrabracad
 * text:    abacadabrabracabracadabrabrabracad
 * pattern: abacad
 *
 * @author liangshanguang
 * @date 03/18/2018
 * @description test
 */
public class P508RabinKarpTest {

    @Test
    public void testMain() {
        String pat = "abracadabra";
        String txt = "abacadabrabracabracadabrabrabracad";

        P508RabinKarp rabinKarp = new P508RabinKarp(pat);
        int offset1 = rabinKarp.search(txt);


        // print results
        System.out.println("text:    " + txt);

        System.out.println("偏移位置：" + offset1);
    }
} 
