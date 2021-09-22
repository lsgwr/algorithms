//вђзгЪ§ 
#include<stdio.h>
#include<string.h>
int p[90];
int v[432];
int pn;
int e[432][90];

void pri()
{
  int i,j;
  pn=0;
  memset(v,0,sizeof(v));
  for(i=2;i<=431;i++)
  {
    if(v[i]==0)
    {
      p[pn++]=i;
      for(j=i;j<=431;j+=i)
        v[j]=1;
    }
  }
}

void fac()
{
  int i,j,k;
  for(i=2;i<=431;i++)
  {
    k=i;
    for(j=0;j<pn;j++)
      while(k>1&&k%p[j]==0)
      { 
        e[i][j]++; 
        k/=p[j];
      }
  }
  for(i=3;i<=431;i++)
    for(j=0;j<pn;j++)
      e[i][j]+=e[i-1][j];
}

int main()
{
  freopen("FactorNumber.in","r",stdin);
  freopen("FactorNumber.out","w",stdout);  
  int i,n,k;
  long long sum;
  pri();
  fac();
  while(scanf("%d%d",&n,&k)!=EOF)
  {
    sum=1;
    for(i=0;i<pn;i++)
      sum*=(1+e[n][i]-e[n-k][i]-e[k][i]);
    printf("%lld\n",sum);
  }
  return 0;
}
