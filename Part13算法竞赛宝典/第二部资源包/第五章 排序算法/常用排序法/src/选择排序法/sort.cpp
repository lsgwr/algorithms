/*
程序名称：选择排序法 
程序说明：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第
二个数和最后一个数比较为止。 选择排序是不稳定的。算法复杂度O(n2)--[n的平方]
程序作者：ZXH (2010-4-2)
程序备注：输入文件为sort.in,输出文件为sort.out,N<=100000
*/ 
#include "stdio.h"
#define N 100000
int a[N+1];
  
void select_sort(int n)
{
 int i, j, min, t;
 for (i=1; i<n; i++)
 {
    min = i; /*假设当前下标为i的数最小，比较后再调整*/
    for (j=i+1; j<=n; j++)/*循环找出最小的数的下标是哪个*/
    {
       if (a[j] < a[min])   
          min = j; /*如果后面的数比前面的小，则记下它的下标*/
    }  
  
    if (min != i) /*如果min在循环中改变了，就需要交换数据*/
    {
       t = a[i];
       a[i] = a[min];
       a[min] = t;
    }
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
      
   select_sort(n);
   
   for(i=1;i<=n;i++)
     printf("%d ",a[i]);
   return 0;
}
