//³ªÆ¬Â¼ÖÆ £­·½·¨£± 
#include<iostream>
#include <cstdlib>
using namespace std;

int g[21][21][21],a[21];

int main()
{
  freopen("record.in","r",stdin);
  freopen("record.out","w",stdout);  
  int n,m,t,l[21];
  cin>>n>>m>>t;
  for(int i=1;i<=n;i++) 
  {  
    cin>>l[i];
    a[i]=1;
  }
  for(int i=1;i<=n;i++)
    for(int j=0;j<=m;j++)
      for(int k=0;k<t;k++)
        if(k>=l[i])
          g[i][j][k]=max(g[i-1][j][k-l[i]]+1,g[i-1][j][k]);
        else
            g[i][j][k]=max(g[i-1][j-1][t-l[i]]+1,g[i-1][j][k]);
  cout<<g[n][m][0]<<endl;
  return 0;
}
