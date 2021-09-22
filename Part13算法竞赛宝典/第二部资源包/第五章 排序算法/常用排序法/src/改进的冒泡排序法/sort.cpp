/*
程序名称：改进的冒泡排序法 
程序说明：从后向前比较，第一轮是a[i]与a[i-1]比较，小的数交换前移，如果有数交换，
记住最后交换的位置，而这位置前的数没有交换，即前面的数已排好序。下一轮扫描则从最
后扫描到最后交换的位置间的相邻数比较。前面的数已无必要比较了，如果数据本身是有序，
则一趟扫描即完成排序，不必象原来一样一定要扫描n-1轮。对部分有序的数排序，可节约
大量时间，但是对于本身无序的数列，并无优势。 
程序作者：ZXH (2010-4-2)
程序备注：输入文件为sort.in,输出文件为sort.out,N<=100000
*/ 
#include <iostream>
using namespace std;
#define N 100000+1
int i,j,n,temp,lastchange;
int a[N+1];

void popsort()
{
  i=2;//从最后向前比较，起始最前面的位置，即n到i间数分别与前一个数比较 
  while((i<n))
  {
    lastchange=n;//先设定这一轮扫描最后交换位置为n 
    for(j=n;j>=i;j--)//从后向前扫描 
      if(a[j]<a[j-1])//相邻前面大于后面的，小的上浮，并记住交换位置j 
      {
        a[j]=a[j]^a[j-1];
        a[j-1]=a[j-1]^a[j];
        a[j]=a[j]^a[j-1];
        lastchange=j;
      }
    i=lastchange;//下一轮，只需要从最后比较到上一轮最后交换的位置lastchange 
  }
}

int main()
{
  freopen("sort.in","r",stdin);
  freopen("sort.out","w",stdout);
  cin>>n;
  for(i=1;i<=n;i++)
    cin>>a[i];
   popsort();//冒泡排序 
  for(i=1;i<=n;i++)
    cout<<a[i]<<' ';
  return 0;
}
