//数的划分－动规算法２ 
#include<fstream>
using namespace std;
long long g[500+1][500+1]={0};
ifstream cin("Numdismantle.in");
ofstream cout("Numdismantle.out");

int main()
{
  int m,k,i=0,j=0;
  cin>>m>>k;
  for(i=1;i<=m;++i)
    g[i-1][0]=1;
  for(i=1;i<m;++i)
    for(j=1;j<=m;++j)
      if(j>=i)
        g[i][j]=g[i-1][j]+g[i][j-i];
      else g[i][j]=g[i-1][j];
  cout<<g[k][m-k]<<endl;
  return 0;
}
