//读写文件方法2 
#include <iostream>
#include <cstdlib>
int main()
{
 int i,len=0,temp[100];
 FILE *in=fopen("a.txt","r");//指针指向输入文件
 FILE *out=fopen("b.txt","w");//输出文件格式 
 for(i=0;!feof(in);i++)//如未到文件末尾
 {
   fscanf(in,"%d",&temp[i]);//读取文件中的数据
   len++;
 }
 for(i=0;i<len-1;i++)  //写入文件
   fprintf(out,"%d ",temp[i]);
 fclose(in);//关闭文件流
 fclose(out);//关闭文件流
}
