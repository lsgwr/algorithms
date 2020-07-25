/*
程序名称：改进的计数排序法 
程序说明：能保持计数的稳定性。该法适用于排序关键字为整数
类型且数值范围较小。时间复杂度为O(n+k),这种线性时间内排
序的效率比基本于比较的排序方法要优越得多
*/ 
#include <iostream>
using namespace std;
#define N 100000
int a[N+1];//a为输入的乱序数组
int b[N+1];//b存放最后排序结果
int c[N+1];//c为辅助数组 

int main()
{
 int n,i,p=0;
 freopen("sort.in","r",stdin);
 freopen("sort.out","w",stdout);
 cin>>n;
 for(i=1;i<=n;i++) //统计 
 {
   cin>>a[i];//如该数为8，则c[8]++
   c[a[i]]++;//注意，考虑到0，因此也包括C[0]
   if(p<a[i])
     p=a[i];//找出最大的数 
 }  
 for(i=1;i<=p;i++)//找出比该数大的数有多少个，即找出该数的最终放置位置 
  c[i]+=c[i-1];
 
 for(i=n;i>=1;i--)//将a中的数逆序放入b数组的应该放的位置，思考为什么逆序 
 {
  b[c[a[i]]]=a[i];
  c[a[i]]--;//该数放置的位置前移了一位，如有相同数，则不会冲突 
 } 
 for(i=1;i<=n;i++)
   cout<<b[i]<<" "; 
 
}
