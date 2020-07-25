/*
程序名称：计数排序法 
程序说明：假设N个输入的每一个都是介于0-K之间的整数，K为整数。对每一个输入元素X，
确定出小于X的元素个数。有了这一信息，将X直接放到它在最终输出数组中的位置上。 
程序作者：ZXH (2010-3-30)
程序备注：输入文件为sort.in,输出文件为sort.out,N<=100000
*/ 

#include <iostream>
using namespace std;
#define N 100000
int a[N+1];//a为输入的乱序数组
int b[N+1];//b存放最后排序结果

int main()
{
 int n,i,p=0;
 freopen("sort.in","r",stdin);
 freopen("sort.out","w",stdout);
 cin>>n;
 for(i=1;i<=n;i++) //统计 
 {
   cin>>a[i];//如该数为8，则c[8]++
   b[a[i]]++;//注意，考虑到0，因此也包括b[0]
   if(p<a[i])
     p=a[i];//找出最大的数，以确定数列的尾 
 }   
 for(i=0;i<=p;i++)
   while(b[i]>0)
   {
     cout<<i<<" ";
     b[i]--; 
   }
}
