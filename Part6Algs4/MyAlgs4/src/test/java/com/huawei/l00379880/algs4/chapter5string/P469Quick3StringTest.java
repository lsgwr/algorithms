package com.huawei.l00379880.algs4.chapter5string;

import com.huawei.l00379880.mylib.file.MyFile;
import org.junit.Test;

import java.util.ArrayList;

/**
 * P469Quick3String Tester.
 *
 * @author liangshanguang
 * @date 03/17/2018
 * @description test
 */
public class P469Quick3StringTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/shells.txt";
        ArrayList<String> strList = MyFile.readLineStrings(filePath);
        String[] a = new String[strList.size()];
        for (int i = 0; i < strList.size(); i++) {
            //System.out.println(strList.get(i));
            a[i] = strList.get(i);
        }
        int N = a.length;
        // sort the strings
        P469Quick3String.sort(a);

        // print the results
        for (int i = 0; i < N; i++) {
            System.out.println(a[i]);
        }

    }
} 
