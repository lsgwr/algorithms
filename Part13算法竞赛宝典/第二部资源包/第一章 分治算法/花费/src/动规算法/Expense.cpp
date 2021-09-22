//花费－动规算法 
#include <iostream>
#include <stdio.h>
#include <string.h>
using namespace std;

int f[1000][1000];
int n,m;
int sum[10000],money[10000];

// f[i][j]=min(f[i-1][k],sum[j]-sum[k])  

void print()
{
  int i,j;	
  for(i=1;i<=m;i++)
  {
    for(j=1;j<=n;j++)
      cout<<f[i][j]<<' ';
    cout<<endl;
  }
}

int main()
{
  freopen("Expense.in","r",stdin);
  freopen("Expense.out","w",stdout);
  int i,j,k,minx,t,maxn;
  while(~scanf("%d%d",&n,&m) && n)
  {
    memset(f,0,sizeof(f));
    memset(sum,0,sizeof(sum));
    memset(money,0,sizeof(money));
    for(i=1;i<=n;i++)
    {
      scanf("%d",&money[i]);
      f[1][i]=f[1][i-1]+money[i];
      sum[i]=sum[i-1]+money[i];
    }
    for(i=2;i<=m;i++)
    {
	  maxn=-1;
	  for(j=1;j<=i;j++)
		maxn=max(money[j],maxn);
      for(j=1;j<=i;j++)
		f[i][j]=maxn;
      for(j=i+1;j<=n;j++)
      { 
		minx=9999999;
		for(k=i-1;k<j;k++)
		{
		  t=max(f[i-1][k],sum[j]-sum[k]);
		  minx=min(t,minx);
        }
		f[i][j]=minx;
      }
    }
    printf("%d\n",f[m][n]);
  }
  return 0;
}
