package com.huawei.l00379880.mylib.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.TreeSet;

/***********************************************************
 * @Title       : MyFile.java
 * @Package     : lsg.huawei.file
 * @Description : 最常用的文件操作，关键在于读取中文没问题！！
 * @author      : l00379880 梁山广
 * @date        : 2016-12-9 上午08:35:06
 * @version     : V1.0
 ***********************************************************/
public class MyFile {
    public static ArrayList<String> readFilesUnderFolder(String filepath, String suffix) {
        ArrayList<String> filePathList = new ArrayList<String>();
        File file = new File(filepath);
        if (!file.isDirectory()) {
            System.out.println("输入参数必须是一个文件夹的路径！");

        } else if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(filepath + "\\" + filelist[i]);
                // 只处理是文件的路径,是文件夹的涉及到下面的递归查找了，后面单独实现
                if (!readfile.isDirectory()) {
                    String totalPath = readfile.getAbsolutePath();
                    String baseName = readfile.getName();
                    if (baseName.endsWith(suffix)) {
                        filePathList.add(totalPath);
                    }
                }
            }
        }
        return filePathList;
    }

    /*************************************************************
     * @Title      : readFilesUnderFolderCursively
     * @Description: 递归查找指定路径下指定后缀的所有文件
     * @param      : @param filepath
     * @param      : @param suffix
     * @param      : @return
     * @return     ：  ArrayList<String> 返回类型 ：
     * @author     : l00379880 梁山广
     * @date       : 2016-12-12 下午11:31:41
     *************************************************************/
    public static ArrayList<String> readFilesUnderFolderCursively(String filepath, String suffix)//先写文件再读文件就好了
    {
        ArrayList<String> filePathList = new ArrayList<String>();
        readFilesUnderFolderCursivelySon(filepath, suffix);
        filePathList = readLineStrings(System.getProperty("user.dir") + "\\temp.txt");
        delete(System.getProperty("user.dir") + "\\temp.txt");
        return filePathList;
    }

    public static ArrayList<String> readFilesUnderFolderCursively(String filepath, String suffix, String encoding)//先写文件再读文件就好了
    {
        ArrayList<String> filePathList = new ArrayList<String>();
        System.out.println(filepath);
        readFilesUnderFolderCursivelySon(filepath, suffix, encoding);

        filePathList = readLineStrings(System.getProperty("user.dir") + "\\temp.txt", encoding);
        delete(System.getProperty("user.dir") + "\\temp.txt");
        return filePathList;
    }

    /**
     * 读取某个文件夹下的所有指定后缀类型的文件，递归查找,因为每一次递归都会重新创建一个list，所以只能靠引用传值
     */
    public static void readFilesUnderFolderCursivelySon(String filepath, String suffix) {
        File file = new File(filepath);
        if (!file.isDirectory()) {
            System.out.println("输入参数必须是一个文件夹的路径！");

        } else if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(filepath + "\\" + filelist[i]);
                //如果是路径的话
                if (!readfile.isDirectory())
                {
                    String totalPath = readfile.getAbsolutePath();
                    if (totalPath.endsWith(suffix)) {
                        //追加要用GBK才能写入中文哦！！
                        fileAppend(System.getProperty("user.dir") + "\\temp.txt", "GBK", totalPath);
                    }

                } else if (readfile.isDirectory()) {
                    //递归查找
                    readFilesUnderFolderCursivelySon(filepath + "\\" + filelist[i], suffix);
                }
            }

        }
    }

    /**
     * 读取某个文件夹下的所有指定后缀类型的文件，递归查找,因为每一次递归都会重新创建一个list，所以只能靠引用传值
     */
    public static void readFilesUnderFolderCursivelySon(String filepath, String suffix, String encoding) {
        File file = new File(filepath);
        if (!file.isDirectory()) {
            System.out.println("输入参数必须是一个文件夹的路径！");

        } else if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(filepath + "\\" + filelist[i]);
                //如果是路径的话
                if (!readfile.isDirectory())
                {
                    String totalPath = readfile.getAbsolutePath();
                    if (totalPath.endsWith(suffix)) {
                        //追加要用GBK才能写入中文哦！！
                        fileAppend(System.getProperty("user.dir") + "\\temp.txt", encoding, totalPath);
                    }

                } else if (readfile.isDirectory()) {
                    //递归查找
                    readFilesUnderFolderCursivelySon(filepath + "\\" + filelist[i], suffix);
                }
            }

        }
    }

    /*************************************************************
     * @Title      : readLineStrings
     * @Description: 按行从文件中读取内容到字符串list
     * @param      : @param filePath
     * @param      : @return
     * @return     ：  ArrayList<String> 返回从文件中督导的字符串list
     * @author     : l00379880 梁山广
     * @date       : 2016-12-9 上午08:36:14
     *************************************************************/
    public static ArrayList<String> readLineStrings(String filePath) {
        ArrayList<String> list = new ArrayList<String>();
        String str = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        BufferedReader br = null;
        try {
            // FileInputStream
            fis = new FileInputStream(filePath);
            // 从文件系统中的某个文件中获取字节
            // InputStreamReader 是字节流通向字符流的桥梁,
            isr = new InputStreamReader(fis, "UTF-8");
            // 从字符输入流中读取文件中的内容,封装了一个new
            br = new BufferedReader(isr);
            // InputStreamReader的对象
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");

        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;//返回list
    }

    /*************************************************************
     * @Title      : readLineStrings
     * @Description: 按照指定的编码格式从文件中逐行读入数据到List，重载了一种方式
     * @param      : @param filePath
     * @param      : @param encoding:指定的编码格式
     * @param      : @return
     * @return     ：  ArrayList<String> 返回类型
     * @author     : l00379880 梁山广
     * @date       : 2016年12月17日 下午11:29:04
     *************************************************************/
    public static ArrayList<String> readLineStrings(String filePath, String encoding) {
        ArrayList<String> list = new ArrayList<String>();
        String str = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        try {
            fis = new FileInputStream(filePath);// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis, encoding);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
            // InputStreamReader的对象
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");

        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;//返回list
    }

    /*************************************************************
     * @Title      : readLineStringsSet
     * @Description: 从文件中读取数据到集合set中，方便后面进行集合的运算
     * @param      : @param filePath
     * @param      : @param encoding
     * @param      : @return
     * @return     ：  TreeSet<String> 返回类型 ：rb文件的路径集合
     * @author     : l00379880 梁山广
     * @date       : 2016年12月22日 下午2:00:53
     *************************************************************/
    public static TreeSet<String> readLineStringsSet(String filePath, String encoding) {
        TreeSet<String> set = new TreeSet<String>();
        String str = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        try {
            fis = new FileInputStream(filePath);// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis, encoding);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
            // InputStreamReader的对象
            while ((str = br.readLine()) != null) {
                set.add(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");

        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return set;//返回list
    }

    /*************************************************************
     * @Title      : readAlltoHTML
     * @Description: 把文本文件中的全部内容读取到一个StringBuilder中
     * @param      : @param filePath
     * @param      : @param encoding
     * @param      : @return
     * @return     ：  String 返回类型
     * @author     : l00379880 梁山广
     * @date       : 2016年12月20日 下午8:43:00
     *************************************************************/
    public static String readAlltoHTML(String filePath, String encoding) {
        StringBuilder result = new StringBuilder("");//新建结果字符串
        ArrayList<String> contentLines = readLineStrings(filePath, encoding);//把脚本文件读取过来
        for (String line : contentLines) {
            result.append(line + "<br>");
        }
        return result.toString();
    }

    /*************************************************************
     * @Title      : readAll
     * @Description: 一次性把文件内容都读到一个ts文件夹
     * @param      : @param filePath
     * @param      : @param encoding
     * @param      : @return
     * @return     ：  String 返回类型
     * @author     : l00379880 梁山广
     * @date       : 2017年2月13日 下午7:35:19
     *************************************************************/
    public static String readAll(String filePath, String encoding) {
        StringBuilder result = new StringBuilder("");//新建结果字符串
        ArrayList<String> contentLines = readLineStrings(filePath, encoding);//把脚本文件读取过来
        for (String line : contentLines) {
            result.append(line + "\n");
        }
        return result.toString();
    }

    /*************************************************************
     * @Title      : fileAppend
     * @Description: 向文件中追加内容，删除需要自己额外操作
     * @param      : @param filePath
     * @param      : @param encoding：最好用UTF-8模式
     * @param      : @param content
     * @return     ：  void 返回类型
     * @author     : l00379880 梁山广
     * @date       : 2016-12-12 下午11:21:52
     *************************************************************/
    public static void fileAppend(String filePath, String encoding, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath, true), encoding));
            out.write(content + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*************************************************************
     * @Title      : fileWrite
     * @Description: 向文件中写入自定义内容，兼容中文，如果文件原来存在
     *               会先删除的，适合一次性写入日志
     * @param      : @param filePath 文件路径
     * @param      : @param encoding 编码
     * @param      : @param content  待写入的内容
     * @return     ：  void 返回类型
     * @author     : l00379880 梁山广
     * @date       : 2016-12-8 下午11:00:12
     *************************************************************/
    public static void fileWrite(String filePath, String encoding, String content) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();//结果文件存在的话先删除，适合写入日志
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            System.out.println("文件夹" + file.getParentFile() + "原来不存在，已经新建好了！");
        }

        try {
            file.createNewFile();
//			System.out.println(filePath+"原来不存在，已经创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(filePath + "创建文件失败唉");
        }
        try {
//			FileOutputStream out = new FileOutputStream(file);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), encoding));
//			byte buy[] = tcContent.getBytes();// 以字节流的方式写入到文件中
//			out.write(buy);
            out.write(content);
            out.close();
//			System.out.println("-----------------"+filePath+"----------写入成功！");
        } catch (FileNotFoundException e) {
            System.out.println(filePath + "写入文件内容失败！文件未找到的错误如下：");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(filePath + "写入文件内容失败！流错误如下：");
            e.printStackTrace();
        }

    }

    /*************************************************************
     * @Title      : exists
     * @Description: 判断文件是够存在
     * @param      : @param filePath
     * @param      : @return
     * @return     ：  boolean 返回类型 :存在的话就返回true
     * @author     : l00379880 梁山广
     * @date       : 2016-12-12 上午11:46:04
     *************************************************************/
    public static boolean exists(String filePath) {
        boolean exists = false;
        File file = new File(filePath);
        if (file.exists()) {
            exists = true;
        }
        return exists;
    }

    /*************************************************************
     * @Title      : delete
     * @Description: 删除指定文件/文件夹
     * @param      : @param filePath
     * @return     ：  void 返回类型
     * @author     : l00379880 梁山广
     * @date       : 2016-12-12 下午11:14:38
     *************************************************************/
    public static void delete(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
//			System.out.println("文件存在的！！");
            file.delete();
        }
    }

    /*************************************************************
     * @Title      : changeCharset
     * @Description: 将字符串的集合进行转化
     * @param      : @param str
     * @param      : @param fromCharset
     * @param      : @param toCharset
     * @param      : @return
     * @param      : @throws Throwable
     * @return     ：  String 返回类型
     * @author     : l00379880 梁山广
     * @date       : 2016年12月23日 上午9:21:18
     *************************************************************/
    public static String changeCharset(String str, String fromCharset, String toCharset) throws Throwable {
        return new String(str.getBytes("GBK"), "UTF-8");
    }

    /*************************************************************
     * @Title      : getBaseName
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param      : @param filePath
     * @param      : @return
     * @return     ：  String 返回类型 :返回取出的文件名
     * @author     : l00379880 梁山广
     * @date       : 2017年1月14日 下午10:47:43
     *************************************************************/
    public static String getBaseName(String filePath) {
        return filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.lastIndexOf("."));
    }

    /*************************************************************
     * @Title      : getRelativePath
     * @Description: 根据文件路径和根路径得到相对路径
     * @param      : @param filePath
     * @param      : @param rootPath
     * @param      : @return
     * @return     ：  String 返回类型
     * @author     : l00379880 梁山广
     * @date       : 2017年1月14日 下午10:50:38
     *************************************************************/
    public static String getRelativePath(String filePath, String rootPath) {
        return filePath.replace(rootPath, "");//直接把路径的无用部分剔除掉
    }

    //删除文件夹
    //param folderPath 文件夹完整绝对路径

    /*************************************************************
     * @Title      : delFolder
     * @Description: 删除指定文件夹
     * @param      : @param folderPath
     * @return     ：  void 返回类型
     * @author     : l00379880 梁山广
     * @date       : 2017年2月14日 上午8:34:04
     *************************************************************/
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除指定文件夹下所有文件
    //param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /*************************************************************
     * @Title      : deleteFolder
     * @Description: 删除文件夹
     * @param      : @param folderPath
     * @return     ：  void 返回类型
     * @author     : l00379880 梁山广
     * @date       : 2017年2月15日 下午3:49:57
     *************************************************************/
    public static void deleteFolder(String folderPath) {
        MyDelete fileDetele = new MyDelete();
        fileDetele.deleteFolder("D:\\MyProjects\\MyEclipse\\ScriptAutoGen\\results\\eSAP\\V200R002C30\\IKE");
    }


    /*************************************************************
     * @Title      : reNameSuffix
     * @Description: 对特定文件重命名, 只修改后缀即可
     * @param      : @param srcPath:原来的文件路径
     * @param      : @param dstSuffix：要修改成的文件后缀,后缀为空就是去掉后缀
     * @return     ：  void 返回类型
     * @author     : l00379880 梁山广
     * @date       : 2017年3月9日 上午9:24:25
     *************************************************************/
    public static void reNameSuffix(String srcPath, String dstSuffix) {
        // 指定文件名及路径
        File file = new File(srcPath);
        String filename = srcPath.substring(0, srcPath.lastIndexOf("."));
        if (dstSuffix.equals("") || dstSuffix == null) {
            // 如果后缀为空表名要去掉后缀
            file.renameTo(new File(filename));
        }
        // 改名
        file.renameTo(new File(filename + "." + dstSuffix));
    }
}