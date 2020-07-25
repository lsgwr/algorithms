//货币系统问题 
#include <stdio.h>
#include <cstdlib>

int v,n,p,i,j;
int f[1001];

int main()
{
  freopen("money.in","r",stdin);
  freopen("money.out","w",stdout);  
  scanf("%d%d",&v,&n);
  f[0]=1;
  for(i=1;i<=v;i++)
  {
    scanf("%d",&p);
    for(j=p;j<=n;j++)
      f[j]+=f[j-p];
  }
  printf("%d\n",f[n]);
  return 0;
}
