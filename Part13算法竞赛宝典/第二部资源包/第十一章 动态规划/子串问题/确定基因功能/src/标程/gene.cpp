//确定基因功能 
#include <iostream>
#include <cstdlib>
#define M 100
using namespace std;

int n,m,k1,k2;
string s1,s2;
int a[M+1][M+1];
int cl[5][5]={5,-1,-2,-1,-3,
              -1,5,-3,-2,-4,
              -2,-3,5,-2,-2,
              -1,-2,-2,5,-1,
              -3,-4,-2,-1,-99};

int ch(char c)
{
  if(c=='A') 
    return 0;
  else if(c=='C') 
    return 1;
  else if(c=='G') 
    return 2;
  else if(c=='T') 
    return 3;
  return 4;
}

void dp()
{
  int i,j,k,t;
  for(i=1;i<=m;++i)
    a[0][i]=a[0][i-1]+cl[ch(s2[i-1])][4];
  for(i=1;i<=n;++i)
    a[i][0]=a[i-1][0]+cl[ch(s1[i-1])][4];
  for(i=1;i<=n;++i)
  {
    for(j=1;j<=m;++j)
    {
      a[i][j]=a[i-1][j-1]+cl[ch(s1[i-1])][ch(s2[j-1])];
      t=a[i][j-1]+cl[ch(s2[j-1])][4];
      if(a[i][j]<t)
        a[i][j]=t;
      t=a[i-1][j]+cl[ch(s1[i-1])][4];
      if(a[i][j]<t)
        a[i][j]=t;
    }
  }
  cout<<a[n][m]<<endl;
}

int main()
{
  freopen("gene.in","r",stdin);
  freopen("gene.out","w",stdout);  
  int k=1;
  cin>>k;
  for(int i=1;i<=k;++i)
  {
    cin>>n>>s1>>m>>s2;
    dp();
  }
  return 0;
}
