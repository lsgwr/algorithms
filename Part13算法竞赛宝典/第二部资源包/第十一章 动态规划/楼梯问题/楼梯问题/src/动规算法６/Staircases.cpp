//楼梯问题－动规算法６ 
#include<iostream>
using namespace std;
long long g[500+1][500+1]={0};

int main()
{
  freopen("Staircases.in","r",stdin);
  freopen("Staircases.out","w",stdout);
  int m=0,i=0,j=0;
  cin>>m;
  for(i=1;i<=m;++i)
    g[i-1][0]=1;
  for(i=1;i<m;++i)
    for(j=1;j<=m;++j)
      if(j>=i)
        g[i][j]=g[i-1][j]+g[i-1][j-i];
      else g[i][j]=g[i-1][j];
  cout<<g[m-1][m]<<endl;
  return 0;
}
