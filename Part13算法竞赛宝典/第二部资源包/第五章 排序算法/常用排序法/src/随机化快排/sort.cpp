/*
程序名称：随机化快速排序法 
程序说明：它首先选一个数组元素（此处随机,而不是中间位置）作为参照，
把比它小的元素放到它的左边，比它大的放在右边。然后运用递归，再将它左，
右两个子数组排序，最后完成整个数组的排序.
程序作者：ZXH (2010-7-28)
程序备注：输入文件为sort.in,输出文件为sort.out,N<=100000
*/ 
#include <iostream>
#include <fstream>
using namespace std;
#define N 100000
int a[N+1];
int n;    
 
void quick(int i,int j)
{
  int m,n,temp;
  int k;
  m=i;
  n=j;
  k = a[rand() % (j - i + 1) + i];
    while(m<=n)
  {
    while(a[m]<k&&m<j)  m++;  /* 从左到右找比k大的元素*/
    while(a[n]>k&&n>i)  n--;  /* 从右到左找比k小的元素*/ 
   
    if(m<=n)
    {               /*若找到且满足条件，则交换*/
       temp=a[m];
       a[m]=a[n];
       a[n]=temp;

      m++;
      n--;
    }
  }
  if(m<j)  quick(m,j);  /*运用递归*/
  if(n>i)  quick(i,n);
}

int main()
{
   freopen("sort.in","r",stdin);
   freopen("sort.out","w",stdout);
   int i;
   scanf("%d",&n); 
   for(i=1;i<=n;i++)
     scanf("%d",&a[i]); 
   srand (time (0));//随机化种子    
   quick(1,n);
  
   for(i=1;i<=n;i++)
     printf("%d ",a[i]);
}
