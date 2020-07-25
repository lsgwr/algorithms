/*
程序名称：插入排序法 
程序说明：插入法是一种比较直观的排序方法。它首先把数组头两个元素排好序，
再依次把后面的元素插入适当的位置。把数组元素插完也就完成了排序
程序作者：ZXH (2010-4-2)
程序备注：输入文件为sort.in,输出文件为sort.out,N<=100000
*/ 
#include "stdio.h"
#define N 100000
int a[N+1];

void insert(int n)
{
  int i,j,temp;
  for(i=2;i<=n;i++)
  {
    temp=a[i];     /*temp为要插入的元素*/
    j=i-1;
    while(j>=1 && temp<a[j]) /*从a[i-1]开始找比a[i]小的数，同时把数组元素向后移*/
    {   
      a[j+1]=a[j];
      j--;
    }
    a[j+1]=temp;  /*插入*/
  }
}

int main()
{
   freopen("sort.in","r",stdin);
   freopen("sort.out","w",stdout);
   int i,n;
   scanf("%d",&n);
   for(i=1;i<=n;i++)
     scanf("%d",&a[i]);   
   insert(n);
   
   for(i=1;i<=n;i++)
     printf("%d ",a[i]);
   return 0;  
}
