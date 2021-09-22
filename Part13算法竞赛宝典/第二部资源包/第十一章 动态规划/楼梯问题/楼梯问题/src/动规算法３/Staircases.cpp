//楼梯问题－动规算法３ 
#include<iostream>
using namespace std;
long long f[505][505],ans;
int n;
int main()
{
  freopen("Staircases.in","r",stdin);
  freopen("Staircases.out","w",stdout);
  cin>>n;
  f[1][1]=1;
  for(int i=2;i<=n;++i)
    for(int j=1;j<i;++j)
      f[i][j]=f[i-j][j]+f[i-j][j-1];
  for(int i=2;i<n;++i)
    ans+=f[n][i];
  cout<<ans<<endl;
  return 0;
}
