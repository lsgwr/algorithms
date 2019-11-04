package com.huawei.l00379880.mylib.file;

import java.io.File;

/***********************************************************
 * @ClassName   : Delete
 * @Description : 文件/文件夹的删除类
 * @author      : l00379880 梁山广
 * @date        : 2017年2月15日 下午3:42:04
 ***********************************************************/
public class MyDelete
{
    private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
    boolean flag = false;
    File file;
    public boolean deleteFolder(String deletePath) {// 根据路径删除指定的目录或文件，无论存在与否
        flag = false;
        if (deletePath.matches(matches)) {
            file = new File(deletePath);
            if (!file.exists()) {// 判断目录或文件是否存在
                return flag; // 不存在返回 false
            } else {

                if (file.isFile()) {// 判断是否为文件
                    return deleteFile(deletePath);// 为文件时调用删除文件方法
                } else {
                    return deleteDirectory(deletePath);// 为目录时调用删除目录方法
                }
            }
        } else {
            System.out.println("要传入正确路径！");
            return false;
        }
    }
    public boolean deleteFile(String filePath) {// 删除单个文件
        flag = false;
        file = new File(filePath);
        if (file.isFile() && file.exists()) {// 路径为文件且不为空则进行删除
            file.delete();// 文件删除
            flag = true;
        }
        return flag;
    }

    public boolean deleteDirectory(String dirPath) {// 删除目录（文件夹）以及目录下的文件
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }
        File dirFile = new File(dirPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        File[] files = dirFile.listFiles();// 获得传入路径下的所有文件
        for (int i = 0; i < files.length; i++) {// 循环遍历删除文件夹下的所有文件(包括子目录)
            if (files[i].isFile()) {// 删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
//				System.out.println(files[i].getAbsolutePath() + " 删除成功");
                if (!flag) {
                    break;// 如果删除失败，则跳出
                }
            } else {// 运用递归，删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;// 如果删除失败，则跳出
                }
            }
        }
//		System.out.println(dirPath+"删除成功！");
        if (!flag) {
            return false;
        }
        if (dirFile.delete()) {// 删除当前目录
            return true;
        } else {
            return false;
        }
    }
}