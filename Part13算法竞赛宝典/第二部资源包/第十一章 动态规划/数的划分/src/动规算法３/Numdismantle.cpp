//数的划分－动规算法３ 
#include<iostream>
#define MM 201
using namespace std;
int f[MM][8],n,kind;

int main()
{
  freopen("Numdismantle.in","r",stdin);
  freopen("Numdismantle.out","w",stdout);
  cin>>n>>kind;
  f[1][1]=1; 
  for(int i=2;i<=n;++i)
    for(int j=1;j<=kind;++j)
      if(i>=j)   
        f[i][j]=f[i-1][j-1]+f[i-j][j];          
  cout<<f[n][kind]<<endl;
  return 0;       
}
