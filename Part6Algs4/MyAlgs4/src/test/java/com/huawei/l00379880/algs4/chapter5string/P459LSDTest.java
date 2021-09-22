package com.huawei.l00379880.algs4.chapter5string;

import com.huawei.l00379880.mylib.file.MyFile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * P459LSD Tester.
 *
 * @author liangshanguang
 * @date 02/26/2018
 * @description test
 */
public class P459LSDTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/words4.txt";
        ArrayList<String> strList = MyFile.readLineStrings(filePath);
        String[] a = new String[strList.size()];
        for (int i = 0; i < strList.size(); i++) {
            System.out.println(strList.get(i));
            a[i] = strList.get(i);
        }
        int n = a.length;

        // check that strings have fixed length
        int w = a[0].length();
//        for (int i = 0; i < n; i++) {
//            System.out.println("a[" + i + "].length = " + a[i].length() + "a[" + i + "] = " + a[i]);
//            System.out.println("w = " + w);
//            assert a[i].length() == w : "Strings must have fixed length";
//        }

        // sort the strings
        P459LSD.sort(a, w);

        // print results
        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
    }
} 
