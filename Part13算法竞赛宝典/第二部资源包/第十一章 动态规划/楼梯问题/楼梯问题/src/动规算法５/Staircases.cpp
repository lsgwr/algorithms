//楼梯问题－动规算法５ 
#include<iostream> 
using namespace std;

long long f[1005];

int main()
{
  freopen("Staircases.in","r",stdin);
  freopen("Staircases.out","w",stdout);
  int n;
  cin>>n;
  f[0]=1;
  for(int i=1;i<n;++i)
    for(int j=n;j>=i;--j)
      f[j]+=f[j-i];
  cout<<f[n]<<endl;
  return 0;
}
