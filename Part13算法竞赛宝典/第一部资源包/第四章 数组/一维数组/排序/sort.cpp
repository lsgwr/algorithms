#include <iostream>
using namespace std;
#define N 100000+1
int i,j,n,temp,lastchange;
int a[N+1];

int main()
{
  freopen("sort.in","r",stdin);
  freopen("sort.out","w",stdout);
  cin>>n;
  for(i=1;i<=n;i++)
    cin>>a[i];
    
  i=2;//从最后向前比较，起始最前面的位置，即n到i间数分别与前一个数比较 
  while((i<n))
  {
    lastchange=n;//先设定这一轮扫描最后交换位置为n 
    for(j=n;j>=i;j--)//从后向前扫描 
      if(a[j]<a[j-1])//相邻前面大于后面的，小的上浮，并记住交换位置j 
      {
        a[j]=a[j]^a[j-1];//两实数交换，此处用的是位运算 
        a[j-1]=a[j-1]^a[j];
        a[j]=a[j]^a[j-1];
        lastchange=j;
      }
    i=lastchange;//下一轮，只需要从最后比较到上一轮最后交换的位置lastchange 
  } 
  for(i=1;i<n;i++)//输出 
    cout<<a[i]<<' ';
  cout<<a[n]<<'\n';  
  return 0;
}
