//øÏ≤ÕŒ Ã‚ 
#include<iostream>
#include <cstring>
#include<cstdlib>
using namespace std;
int a,b,c,p1,p2,p3,n,f[11][101][101],t[11];

int main()
{
  freopen("FastFood.in","r",stdin);
  freopen("FastFood.out","w",stdout);
  cin>>a>>b>>c>>p1>>p2>>p3>>n;
  for(int i=1;i<=n;i++) 
    cin>>t[i];
  int minn=min(100/a,min(100/b,100/c));
  memset(f,-1,sizeof(f));
  f[0][0][0]=0;
  for(int i=1;i<=n;i++)
    for(int j=0;j<=minn*a;j++)
      for(int k=0;k<=minn*b;k++)
        for(int j1=0;j1<=j;j1++)
          for(int k1=0;k1<=k;k1++)
            if(f[i-1][j-j1][k-k1]!=-1 && t[i]-j1*p1-k1*p2>=0)
            {
              if(f[i][j][k] >= minn*c)
              {
                j1=j+1;
                break;
              }
              f[i][j][k]=max(f[i][j][k],f[i-1][j-j1][k-k1]+(t[i]-j1*p1-k1*p2)/p3);
            }
  int ans=0;
  for(int i=0;i<=minn*a;i++)
    for(int j=0;j<=minn*b;j++)
      ans=max(ans,min(i/a,min(j/b,f[n][i][j]/c)));
  cout<<ans<<endl;
}
