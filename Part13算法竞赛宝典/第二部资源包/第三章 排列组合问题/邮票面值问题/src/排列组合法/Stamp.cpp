//邮票面值问题－排列组合法 
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;
int buff[40],num[40];         /*存放排列组合的数组*/
int link[200];    /*存放可连续组合成的值，1代表有，0代表无*/
int Max=0;              /*表示最大连续值*/
int answer[40];       /*表示最终的邮票面值*/
int N,KK,sum=0;

void sort(int k)/*将要输出的数组重新排序*/
{  int t,j,i;
   for(j=0;j<k-1;j++)  
     for(i=0;i<k-j-1;i++)
      if(answer[i]>answer[i+1])
         {t=answer[i];answer[i]=answer[i+1];answer[i+1]=t;}
}
void print()
{  int i;
   for(i=0;i<KK;i++)
     printf("%d ",answer[i]);
   printf("%d\n",Max);  /*此处输出结果*/
}

void finish()/*比较出最大的连续值及存入各种邮票值*/
{ int i;
  for(i=1;link[i]!=0;i++)
   {   } 
   if(Max<i-1)
   {
      Max=i-1;   
      for(int j=0;j<KK;j++)
        answer[j]=buff[j];
   }    
   for(i=0;i<200;i++)
      link[i]=0;
      
}

void writelink(int n,int m)/*n为邮票总数量 m为邮票共多少种类*/
{  int i;                        /*将所有可能取得的值存入数组中*/
   if(n<0||m==0)
   { 
     sum=0;
     for(int u=0;u<KK;u++)  
        sum=sum+num[u+1]*buff[u];
     if(sum<200 && link[sum]!=1)   /*防止SUM值过大，超过数组范围而破坏其他数据*/
      link[sum]=1;
     
     return;
    } 
   for(i=n;i>=0;i--)  /*递归列出所有排列*/
   {
       num[m]=i;
       writelink(n-i,m-1);      
   }    
}

void comb(int m,int k)/*从2开始递归打出所有组合*/
{ if(m>55||k==0)
  {  buff[0]=1;
     writelink(N,KK);
     finish();
     return;
  } 
  for( int i=m; i<= 55; i++)/*递归打出所有排列*/
  {
     buff[k] =i;
     comb(i+1,k-1);
  }
}

main()
{  
  freopen("Stamp.in","r",stdin);
  freopen("Stamp.out","w",stdout);
  scanf("%d %d",&N,&KK);
 
  comb(2,KK-1);
  sort(KK);
  print();
}
