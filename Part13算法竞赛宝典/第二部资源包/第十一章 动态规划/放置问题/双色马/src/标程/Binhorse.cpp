//Ë«É«Âí 
#include<stdio.h>
#include<stdlib.h>
#define T 99999999

int f[501][501],a[501],s[501];

int main()
{
  freopen("Binhorse.in","r",stdin);
  freopen("Binhorse.out","w",stdout);
  int i,j,k,d,n;
  int Min;
  scanf("%d %d",&n,&k);
  for(i=1;i<=n;i++)
  {
    f[0][i]=0; 
    f[i][0]=T; 
    f[0][0]=0;
  }
  for(i=1;i<=n;i++)
    scanf("%d",&a[i]);
  for(i=1;i<=n;i++)
  {
    if(a[i]==1)
      s[i]=s[i-1]+1;
    else 
      s[i]=s[i-1];
  }
  for(j=1;j<=k;j++)
    for(i=1;i<=n;i++)
    { 
      Min=T;
      for(d=0;d<i;d++)
      if(Min>f[d][j-1]+(s[i]-s[d])*(i-d-(s[i]-s[d])))
      {
        Min=f[d][j-1]+(s[i]-s[d])*(i-d-(s[i]-s[d]));
        f[i][j]=Min;
      }
    }
  printf("%d\n",Min);
  return 0;
}
