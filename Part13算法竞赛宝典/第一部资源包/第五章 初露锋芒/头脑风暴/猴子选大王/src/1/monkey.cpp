//猴子选大王的优化算法1
#include <iostream>
using namespace std;
#define MAX 10000000
long a[MAX+1];
long m,n,i,Count,k;
int main()
{
  freopen("monkey.in","r",stdin);
  freopen("monkey.out","w",stdout);  
  cin>>m>>n;
  for(i=1;i<=m-1;i++)
    a[i]=i+1;
  a[m]=1;//最后一只猴子指向第一只  
  k=m;//报数前，指向该报数的前一只猴子 
  for(i=1;i<=m;i++)//出圈M只猴子 
  {
    for(Count=1;Count<=n-1;Count++)//只须报数n-1次，a[k]指向该出圈猴子 
      k=a[k];
    a[k]=a[a[k]];                              
  }
  cout<<k<<endl;//最后一只出圈的是大王 
  return 0;
}
