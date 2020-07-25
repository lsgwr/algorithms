//”≤±“Œ Ã‚ 
#include<cstdio>
#include<cstring>
#include<iostream>
using namespace std;
const int maxn=101;
int n,m,ans,num[maxn],c[maxn],dp[100010];
void CompletePack(int cost,int weight)
{
    for(int i=cost;i<=m;i++)
	dp[i]=max(dp[i],dp[i-cost]+weight);
}
void ZeroOnePack(int cost,int weight)
{
    for(int i=m;i>=cost;i--)
	dp[i]=max(dp[i],dp[i-cost]+weight);
}
void MultiplyPack(int cost,int weight,int amount)
{
    if(cost*amount>=m)
	CompletePack(cost,weight);
    else
    {
	int k=1;
	while(k<amount)
	{
	    ZeroOnePack(k*cost,k*weight);
	    amount-=k;
	    k<<=1;
	}
	ZeroOnePack(amount*cost,amount*weight);
    }
}
int main()
{
   freopen("coin.in","r",stdin);
   freopen("coin.out","w",stdout); 
    while(scanf("%d%d",&n,&m)&&(n+m))
    {
	ans=0;
	memset(dp,0,sizeof(dp));
	for(int i=0;i<n;i++)
	    scanf("%d",&c[i]);
	for(int i=0;i<n;i++)
	    scanf("%d",&num[i]);
	for(int i=0;i<n;i++)
	    MultiplyPack(c[i],c[i],num[i]);
	for(int i=1;i<=m;i++)
	    if(dp[i]==i)
		ans++;
	printf("%d\n",ans);
    }
    return 0;
}
