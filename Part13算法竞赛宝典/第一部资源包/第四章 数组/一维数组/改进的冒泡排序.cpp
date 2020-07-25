//改进的冒泡排序
#include <iostream>
using namespace std;
#define n 5
int i,j,temp,lastchange;
int a[n+1];
int main()
{
  for(i=1;i<=n;i++)
    cin>>a[i];
  i=2;
  while((i<n))
  {
    lastchange=n;
    for(j=n;j>=i;j--)//从后向前比较 
      if(a[j]<a[j-1])
      {
        temp=a[j];
        a[j]=a[j-1];
        a[j-1]=temp;
        lastchange=j;
      }
    i=lastchange;
  }
  for(i=1;i<=n;i++)
    cout<<a[i]<<' ';
    system("pause");
  return 0;
}
