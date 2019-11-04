package com.huawei.l00379880.algs4.chapter5string;

import com.huawei.l00379880.mylib.file.MyFile;
import org.junit.Test;

import java.util.ArrayList;

/**
 * P462MSD Tester.
 *
 * @author liangshanguang
 * @date 03/17/2018
 * @description test
 */
public class P462MSDTest {

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
        P462MSD.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
} 
