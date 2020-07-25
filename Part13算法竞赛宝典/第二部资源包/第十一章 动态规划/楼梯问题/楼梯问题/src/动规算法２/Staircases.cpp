//楼梯问题－动规算法２ 
#include<iostream>
using namespace std;
long long f[505][505],ans;
int n;
int main()
{
  freopen("Staircases.in","r",stdin);
  freopen("Staircases.out","w",stdout);
  cin>>n;
  for(int i=0;i<=n;++i)
    f[0][i]=1;
  for(int i=1;i<n;++i)
  {
    for(int j=i;j<=n;++j)
      f[j][i]=f[j-i][i-1]+f[j][i-1];
    for(int j=1;j<i;++j)
      f[j][i]=f[j][i-1];
  }       
  cout<<f[n][n-1]<<endl;
  return 0;
}
