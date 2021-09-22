//完全背包问题 
#include<iostream>
#include<cstdlib>
using namespace std;
int f[10001];
int main()
{
  freopen("FullKnapsack.in","r",stdin);
  freopen("FullKnapsack.out","w",stdout);  
  int m,n,w=0,c=0;
  cin>>m>>n;
  for(int i=1;i<=n;i++)
  {
    cin>>w>>c;
    for(int j=w;j<=m;++j)
      f[j]=(f[j-w]+c)>f[j]?(f[j-w]+c):f[j];
  }
  cout<<f[m]<<endl;
  return 0;
}
