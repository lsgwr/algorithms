/*
冒泡法
其原理为从a[0]开始，依次将其和后面的元素比较,
若a[0]>a[i]，则交换它们，一直比较到a[n]。
同理对a[1],a[2],...a[n-1]处理，即完成排序。
*/

#include "stdio.h"
int a[100000];

void bubble(int n) /*定义两个参数：数组首地址与数组大小*/
{
 int i,j,temp;
 for(i=0;i<n-1;i++)
   for(j=i+1;j<n;j++)  /*注意循环的上下限*/
   if(a[i]>a[j])  {
     temp=a[i];
     a[i]=a[j];
     a[j]=temp;
   }
}

main()
{
   int i,n;   
   freopen("sort.in","r",stdin);
   freopen("sort.out","w",stdout);   
   scanf("%d",&n);
   for(i=0;i<n;i++)
     scanf("%d",&a[i]);
     
   bubble(n);
   
   for(i=0;i<n;i++)
     printf("%d ",a[i]);
}
