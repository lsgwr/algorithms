//ºÏ³ªÍÅ 
#include<stdio.h>
#include<stdlib.h>
int T[201];
int f[201],g[201];

int main()
{
  freopen("chorus.in","r",stdin);
  freopen("chorus.out","w",stdout);
  int n,i,j,min,k,R,L;
  scanf("%d",&n);
  for(i=1;i<=n;i++)
    scanf("%d",&T[i]);
  for(i=1;i<=n;i++)
  {
    f[i]=1;
    for(j=1;j<=i-1;j++)
      if(T[j]<T[i]&&f[j]+1>f[i])
        f[i]=f[j]+1;
  }
  for(i=n;i>=1;i--)
  {
    g[i]=1;
    for(j=i+1;j<=n;j++)
      if(T[j]<T[i]&&g[j]+1>g[i])
        g[i]=g[j]+1;
  }
  min=n;
  for(k=1;k<=n;k++)
  {
    L=f[k],R=g[k];
    if(n-L-R+1<min)
      min=n-L-R+1;
  }
  printf("%d\n",min);
}
