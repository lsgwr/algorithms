//sort
#include <iostream>
using namespace std;
#define N 100000
int a[N+1];
int n;    
 
void QuickSort(int i,int j)
{
  int m,n,temp;
  int k;
  m=i;
  n=j;
  k=a[(i+j)/2];  /*选取的参照*/
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
  if(m<j)  QuickSort(m,j);  /*运用递归*/
  if(n>i)  QuickSort(i,n);
}

int main()
{
   freopen("sort.in","r",stdin);
   freopen("sort.out","w",stdout);
   int i;
   scanf("%d",&n); 
   for(i=1;i<=n;i++)
     scanf("%d",&a[i]);  
   QuickSort(1,n);
  
   for(i=1;i<=n;i++)
     printf("%d ",a[i]);
}
