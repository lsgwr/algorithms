import os

def isFile(filePath):#修改文件扩展名
    filename=filePath.split('\\')[-1]#拆分文件路径获得文件名
    fatherPath=filePath.replace(filename,'')#获得父级路径
    split=os.path.splitext(filename)#拆分文件名和扩展名
    if split[1] == '.ans':
        newname=split[0]+'.out'#生成新文件名
        os.chdir(fatherPath)#改变当前工作目录到指定的路径
        if os.path.exists(newname):
            return
        os.rename(filename,newname)#文件重命名

def openDir(filePath):#递归文件夹
    pathDir=os.listdir(filePath)#返回包含的文件或文件夹的名字的列表
    for filename in pathDir:#遍历列表
        childPath=os.path.join(filePath,filename)
        #判断是否为文件夹
        if os.path.isfile(childPath):
            isFile(childPath)
        else:
            openDir(childPath)


rootDir=r'E:\code\github\algorithms\Part13算法竞赛宝典'#根目录
pathDir =  os.listdir(rootDir)#列出根目录下所有内容

for allDir in pathDir:#遍历列表
    filepath=os.path.join(rootDir,allDir)#文件路径合成
    
    #判断是否为文件夹
    if os.path.isfile(filepath):
        isFile(filepath)
    else:
        openDir(filepath)