//最大连续子序列和－动规算法 
#include <iostream>
#include <cstdlib>
using namespace std;
int n,sum,Max=-INT_MAX,a[100001];

int main()
{
  freopen("Csum.in","r",stdin);
  freopen("Csum.out","w",stdout);
  cin>>n;
  for(int i=1;i<=n;++i)
    cin>>a[i];
  sum=0;
  for (int i=1;i<=n;++i)
  {
    sum+=a[i];
    if(sum>Max)  
      Max=sum;
    if(sum<0)
      sum=0;
  }
  cout<<Max<<endl;
  return 0;
}
