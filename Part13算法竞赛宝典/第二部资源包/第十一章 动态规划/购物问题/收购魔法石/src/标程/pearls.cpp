//ÊÕ¹ºÄ§·¨Ê¯ 
#include<stdio.h>
#include<stdlib.h>
int opt[1001][1001],price[1001],dj[1001];

int main()
{
  freopen("pearls.in","r",stdin);
  freopen("pearls.out","w",stdout);  
  int i,j,t,n,min=99999999;
  scanf("%d",&n);
  for(i=1;i<=n;i++)
    scanf("%d %d",&dj[i],&price[i]);
  opt[n][n]=(dj[n]+10)*price[n];
  for(i=n;i>=2;i--)
    for(j=i;j<=n;j++)
      if(opt[i][j]>0)
      {
        t=opt[i][j]+dj[i-1]*price[j];
        if(opt[i-1][j]==0||t<opt[i-1][j])
          opt[i-1][j]=t;
        t=opt[i][j]+(dj[i-1]+10)*price[i-1];
        if(opt[i-1][i-1]==0||t<opt[i-1][i-1])
          opt[i-1][i-1]=t;
      }
  for(i=1;i<=n;i++)
    if(opt[1][i]<min)
      min=opt[1][i]; 
  printf("%d\n",min);
  return 0;
}
