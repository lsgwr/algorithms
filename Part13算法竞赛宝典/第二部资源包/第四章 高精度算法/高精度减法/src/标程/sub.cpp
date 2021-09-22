//简单高精度减法
#include <iostream>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#define MAXN 5001
using namespace std;

void sub(int x[],int y[],int len,int on)//on：是否输出负数的开关 
{ 
  for(int i=0;i<len;i++) //减法计算 
  {
     x[i]-=y[i];//此程序是将结果覆盖保存在现有的x[]数组
     if(x[i]<0)
     {
       --x[i+1];//借位 
       x[i]+=10;
     }
     if(x[len-1]==0) 
       --len;
   }
   for(int i=len-1;i>=0;--i)//如最前位的数为0,则消去 
   {
     if(x[i]==0) 
       len--;
     if(x[i]!=0)
        break;
   }
   if (on==1) 
     x[len-1]=-x[len-1];//如结果为负数,则将最前一位变为负数 

   for(int i=len-1;i>=0;--i)  //输出结果 
     cout<<x[i];
   cout<<"\n";
}

void init(int x[],char temp[],int len)//字符数组转换为整型数组 
{
  for(int i=0;i<len;i++)
    x[i]=temp[len-1-i]-48;//倒序转换 
}

int main()
{
  freopen("sub.in","r",stdin);//使用文件重定向，则头文件需包含stdio.h,stdlib.h 
  freopen("sub.out","w",stdout);//否则linux环境下可能会编译不通过 
  char str1[MAXN],str2[MAXN];
  int la,lb,k,a[MAXN]={0},b[MAXN]={0};
  cin>>str1>>str2;
  la=strlen(str1);
  lb=strlen(str2);
  init(a,str1,la);//初始化 
  init(b,str2,lb);

  if(la>lb)//如果a的位数比b的位数多 
    sub(a,b,la,0);//则计算a-b的值,0表示输出为正值 
  else if(la<lb) 
    sub(b,a,lb,1);//否则计算b-a的值，1表示输出为负值 
  else //如果两个数位数相同 
  {
    k=strcmp(str1,str2);//比较两字符数组大小 
    if(k==0)
      cout<<"0\n";
    else if(k>0)
      sub(a,b,la,0);
    else
      sub(b,a,lb,1);   
  } 
  return 0;
}  
