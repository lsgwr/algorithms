//收益 
#include <iostream>
using namespace std;
#define MAXV 50000
#define max(a,b) a>b?a:b

int main()
{
  freopen("Profit.in","r",stdin);
  freopen("Profit.out","w",stdout);
  int dp[MAXV];
  int v[11],c[11];
  int n,m,year,t,i,j,sum,k;
  scanf("%d",&n);
  while(n--)
  {
    scanf("%d%d%d",&m,&year,&t);
    for(i=1;i<=t;i++)
    {
      scanf("%d%d",&v[i],&c[i]);
      v[i]=v[i]/1000;//因为价值是1000的倍数，所以除以1000
    }
    sum=0;
    for(i=0;i<year;i++)//因为每年都要投资，所以每年都要计算
    {			
      memset(dp,0,sizeof(dp));	//对数组进行初始化
      sum=m/1000;		//对现在第i年投资前，拥有多少钱
      for(j=1;j<=t;j++)	//完全背包DP，当今年投资在前j个股票的价值最大DP[k]
      {		
		for(k=0;k<=sum;k++)
		  if(k>=v[j])
		    dp[k]=max(dp[k],dp[k-v[j]]+c[j]);
      }
      m+=dp[sum];//将今年赚到的作为下一年的初始资金 
    }
    printf("%d\n",m);
  }
  return 0;
}
